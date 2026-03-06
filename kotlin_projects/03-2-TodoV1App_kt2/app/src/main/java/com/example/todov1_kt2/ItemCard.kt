package com.example.todov1_kt2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todov1_kt2.ui.theme.TodoV1_kt2Theme

@Composable
fun ItemCard(
    task: Task,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = "check icon")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = task.name)
    }

}

@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    TodoV1_kt2Theme {
        ItemCard(
            Task("Task 1", "Task 1 description")
        )
    }
}