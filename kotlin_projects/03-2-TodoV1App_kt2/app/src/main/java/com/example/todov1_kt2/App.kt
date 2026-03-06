package com.example.todov1_kt2

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todov1_kt2.ui.theme.TodoV1_kt2Theme

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // keep tasks in memory
    val tasks = remember { mutableStateListOf<Task>() }
    var showAlertDialog by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = backStackEntry?.destination?.route != "ListTask",
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "ListTask",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("ListTask") {
                ListTask(
                    tasks = tasks,
                    onAddTaskButtonClicked = {
                        // open new task screen
                        navController.navigate("NewTask")
                    },
                    onItemClicked = { index ->
                        Log.i("App", "Clicked index: " + index)
                        Log.i("App", "list: " + tasks)
                        showAlertDialog = true // open dialog (it may remove the task)
                        currentIndex = index
                    }
                )
            }
            composable("NewTask") {
                NewTask(onAddTaskButtonClicked = { name, description ->
                    val newTask = Task(name, description)
                    tasks.add(newTask)
                    navController.navigateUp()
                })
            }
        }
    }

    when {
        showAlertDialog -> {
            AlertDialog(
                onDismissRequest = { showAlertDialog = false },
                onConfirmation = {
                    tasks.removeAt(currentIndex)
                    showAlertDialog = false
                },
                dialogTitle = "Name: ${tasks.get(currentIndex).name}",
                dialogText = "Description: ${tasks.get(currentIndex).description}; have you finished it?",
                icon = Icons.Default.Delete
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    TodoV1_kt2Theme {
        App()
    }
}