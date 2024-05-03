package com.example.todov1_kt2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todov1_kt2.ui.theme.TodoV1_kt2Theme

@Composable
fun ListTask(
    tasks: List<Task>,
    onAddTaskButtonClicked : () -> Unit = {},
    onItemClicked: (Int) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = onAddTaskButtonClicked) {
            Text(text = stringResource(id = R.string.add_task))
        }
        Divider(modifier = Modifier.padding(8.dp))
        LazyColumn {
            itemsIndexed(items=tasks) { index, item ->
                ItemCard(
                    task = item,
                    onClick = { onItemClicked(index) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListTaskPreview() {
    TodoV1_kt2Theme {
        ListTask(
            tasks = ((1..5).map { Task("Task " + it, "Task " + it) })
//            tasks = listOf(
//                Task("Task 1", "Task 1"),
//                Task("Task 2", "Task 2"),
//            )
        )
    }
}