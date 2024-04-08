package com.example.imcapp_kt2

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.imcapp_kt2.ui.theme.ImcApp_kt2Theme

@Composable
fun NormalAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NormalAlertDialogPreview() {
    ImcApp_kt2Theme {
        NormalAlertDialog(
            onDismissRequest = { /*TODO*/ },
            onConfirmation = { /*TODO*/ },
            dialogTitle = "Alert dialog example",
            dialogText = "This is an example of an alert dialog with buttons.",
            icon = Icons.Default.Info
        )
    }
}