package com.example.todov1_kt2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todov1_kt2.ui.theme.TodoV1_kt2Theme

@Composable
fun NewTask(
    onAddTaskButtonClicked: (String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "New Task To Do")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Task Name") }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Task Description") }
        )
        Button(onClick = { onAddTaskButtonClicked(name, description) }) {
            Text(text = "Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewTaskPreview() {
    TodoV1_kt2Theme {
        NewTask(onAddTaskButtonClicked = { s1, s2 -> })
    }
}