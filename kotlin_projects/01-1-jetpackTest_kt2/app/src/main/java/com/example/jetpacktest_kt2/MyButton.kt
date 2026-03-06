package com.example.jetpacktest_kt2

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(
    label: String,
    hasIcon: Boolean = false,
    marked: Boolean = false,
    onClicked: () -> Unit = {}
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onClick = onClicked
    ) {
        if (hasIcon) {
            Icon(
                imageVector = if (marked) Icons.Filled.CheckCircle else Icons.Filled.Check,
                contentDescription = "check"
            )
            Spacer(modifier = Modifier.size(20.dp))
        }
        Text(
            text = label,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview() {
    MyButton("Hello")
}