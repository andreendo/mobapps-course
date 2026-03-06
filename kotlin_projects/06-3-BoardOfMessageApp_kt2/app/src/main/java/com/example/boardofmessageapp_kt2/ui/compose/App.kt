package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boardofmessageapp_kt2.ui.theme.BoardOfMessageApp_kt2Theme
import kotlinx.coroutines.launch

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val mainViewModel = viewModel<MainViewModel>()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = backStackEntry?.destination?.route != "Initial",
                navigateUp = { navController.navigateUp() }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Initial",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("Initial") {
                InitialScreen(
                    mainViewModel,
                    onBoardMessagesClicked = { navController.navigate("BoardMessages") },
                    onPostMessageClicked = { navController.navigate("PostMessage") },
                    onDeleteBoardClicked = { navController.navigate("DeleteBoard") }
                )
            }
            composable("PostMessage") {
                PostMessage(mainViewModel)
            }
            composable("BoardMessages") {
                BoardMessages(mainViewModel)
            }
            composable("DeleteBoard") {
                DeleteBoard(mainViewModel)
            }
        }
    }

    if (mainViewModel.showNetworkErrorSnackBar) {
        LaunchedEffect(mainViewModel.showNetworkErrorSnackBar) {
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    "Network error. Try again later!",
                    actionLabel = "OK"
                )
                when(result) {
                    SnackbarResult.Dismissed -> mainViewModel.dismissNetworkErrorSnackBar()
                    SnackbarResult.ActionPerformed -> mainViewModel.dismissNetworkErrorSnackBar()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    BoardOfMessageApp_kt2Theme {
        App()
    }
}