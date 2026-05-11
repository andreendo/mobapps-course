package com.example.videocapture

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("MissingPermission")
@Composable
fun CameraVideoScreen(viewModel: AppViewModel) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCameraPermission by remember { mutableStateOf(false) }
    var hasAudioPermission by remember { mutableStateOf(false) }

    // 🔐 CAMERA PERMISSION
    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasCameraPermission = granted
        }

    // 🔐 AUDIO PERMISSION
    val audioPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasAudioPermission = granted
        }

    LaunchedEffect(Unit) {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        audioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // =========================================
        // CAMERA PREVIEW
        // =========================================

        AndroidView(
            modifier = Modifier.weight(1f),
            factory = { ctx ->

                val previewView = PreviewView(ctx)

                val cameraProviderFuture =
                    ProcessCameraProvider.getInstance(ctx)

                cameraProviderFuture.addListener({

                    val cameraProvider =
                        cameraProviderFuture.get()

                    // 🔴 PREVIEW
                    val preview = androidx.camera.core.Preview.Builder()
                        .build()

                    preview.setSurfaceProvider(
                        previewView.surfaceProvider
                    )

                    // 🎥 RECORDER
                    val recorder = Recorder.Builder()
                        .setQualitySelector(
                            QualitySelector.from(Quality.HD)
                        )
                        .build()

                    val videoCapture =
                        VideoCapture.withOutput(recorder)

                    viewModel.videoCapture = videoCapture

                    val cameraSelector =
                        CameraSelector.DEFAULT_BACK_CAMERA

                    cameraProvider.unbindAll()

                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        videoCapture
                    )

                }, ContextCompat.getMainExecutor(ctx))

                previewView
            }
        )

        // =========================================
        // CONTROLS
        // =========================================

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!viewModel.isRecording) {

                Button(
                    onClick = {
                        if (!hasCameraPermission ||
                            !hasAudioPermission
                        ) {
                            return@Button
                        }

                        startRecording(
                            context,
                            viewModel
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start Recording")
                }

            } else {

                Button(
                    onClick = {
                        stopRecording(viewModel)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Stop Recording")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🎥 VIDEO RESULT
            viewModel.videoUri?.let { uri ->

                Text("Video recorded successfully!")

                Spacer(modifier = Modifier.height(12.dp))

                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(uri.toString())
            }
        }
    }
}

// =========================================
// START RECORDING
// =========================================

@RequiresPermission(Manifest.permission.RECORD_AUDIO)
fun startRecording(
    context: Context,
    viewModel: AppViewModel
) {

    val name = SimpleDateFormat(
        "yyyy-MM-dd-HH-mm-ss",
        Locale.US
    ).format(System.currentTimeMillis())

    val contentValues = ContentValues().apply {

        put(
            MediaStore.MediaColumns.DISPLAY_NAME,
            name
        )

        put(
            MediaStore.MediaColumns.MIME_TYPE,
            "video/mp4"
        )

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(
                MediaStore.Video.Media.RELATIVE_PATH,
                "Movies/CameraX-Video"
            )
        }
    }

    val mediaStoreOutputOptions =
        MediaStoreOutputOptions.Builder(
            context.contentResolver,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
            .setContentValues(contentValues)
            .build()

    val recording = viewModel.videoCapture.output
        .prepareRecording(
            context,
            mediaStoreOutputOptions
        )
        .withAudioEnabled()
        .start(
            ContextCompat.getMainExecutor(context)
        ) { event ->

            when (event) {

                is VideoRecordEvent.Start -> {
                    viewModel.setRecordingState(true)
                }

                is VideoRecordEvent.Finalize -> {

                    if (!event.hasError()) {

                        viewModel.setVideo(
                            event.outputResults.outputUri
                        )

                    }

                    viewModel.setRecordingState(false)
                    viewModel.updateRecording(null)
                }
            }
        }

    viewModel.updateRecording(recording)
}

// =========================================
// STOP RECORDING
// =========================================

fun stopRecording(viewModel: AppViewModel) {

    viewModel.recording?.stop()

    viewModel.updateRecording(null)

    viewModel.setRecordingState(false)
}