package com.example.videocapture

import android.net.Uri
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class AppViewModel : ViewModel() {

    var videoUri by mutableStateOf<Uri?>(null)
        private set

    var isRecording by mutableStateOf(false)
        private set

    var recording by mutableStateOf<Recording?>(null)
        private set

    lateinit var videoCapture: VideoCapture<Recorder>

    fun setVideo(uri: Uri) {
        videoUri = uri
    }

    fun setRecordingState(value: Boolean) {
        isRecording = value
    }

    fun updateRecording(record: Recording?) {
        recording = record
    }
}