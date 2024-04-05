package com.example.toastdialogapp_kt2

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.toastdialogapp_kt2.ui.theme.ToastDialogApp_kt2Theme

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val activity = (context as? Activity)

    val openMinimalDialog = remember { mutableStateOf(false) }
    val openNormalDialog = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            val toast = Toast.makeText(context, R.string.short_toast_msg, Toast.LENGTH_SHORT)
            toast.show()
        }) {
            Text(text = stringResource(id = R.string.show_toast1))
        }
        Button(onClick = {
            val toast = Toast.makeText(context, R.string.long_toast_msg, Toast.LENGTH_LONG)
            // This method is not working for devices running API30 or higher
            // Source: https://stackoverflow.com/questions/65004242/toast-setgravity-does-not-work-in-my-avd-nexus-6-api-30
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show()
        }) {
            Text(text = stringResource(id = R.string.show_toast2))
        }
        Button(onClick = {
            openMinimalDialog.value = !openMinimalDialog.value
        }) {
            Text(text = stringResource(id = R.string.show_empty_dialog))
        }
        Button(onClick = {
            openNormalDialog.value = !openNormalDialog.value
        }) {
            Text(text = stringResource(id = R.string.exit_with_dialog))
        }
    }

    when {
        openMinimalDialog.value -> {
            MinimalDialog(stringResource(id = R.string.show_empty_dialog), onDismissRequest = {
                openMinimalDialog.value = false
            })
        }

        openNormalDialog.value -> {
            NormalAlertDialog(
                onDismissRequest = {
                    openNormalDialog.value = false
                },
                onConfirmation = {
                    activity?.finish()
                },
                dialogTitle = "Confirmation",
                dialogText = "Are you sure?",
                icon = Icons.Default.Info
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ToastDialogApp_kt2Theme {
        MainScreen()
    }
}