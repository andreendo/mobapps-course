package com.example.imcapp_kt2

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.imcapp_kt2.ui.theme.ImcApp_kt2Theme

@Composable
fun App(
    imcViewModel: ImcViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    var openDialog by remember { mutableStateOf(false) }
    val uiState by imcViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = backStackEntry?.destination?.route != "Main",
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Main",
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable("Main") {
                MainForm(
                    name = uiState.name,
                    age = uiState.age,
                    height = uiState.height,
                    weight = uiState.weight,
                    onNameChanged = { imcViewModel.updateName(it) },
                    onAgeChanged = { imcViewModel.updateAge(it) },
                    onHeightChanged = { imcViewModel.updateHeight(it) },
                    onWeightChanged = { imcViewModel.updateWeight(it) },
                    onCalculateButtonClicked = {
                        navController.navigate("Confirm")
                    }
                )
            }
            composable("Confirm") {
                ConfirmForm(
                    name = uiState.name,
                    age = uiState.age,
                    height = uiState.height,
                    weight = uiState.weight,
                    onCalculateButtonClicked = {
                        imcViewModel.calculateImc()
                        openDialog = true
                    }
                )
            }
        }
    }

    when {
        openDialog -> {
            NormalAlertDialog(
                onDismissRequest = {
                    openDialog = false
                    navController.navigateUp()
                },
                onConfirmation = {
                    openDialog = false
                    navController.navigateUp()
                },
                dialogTitle = stringResource(id = R.string.imc_calculation),
                dialogText = imcViewModel.dialogMessage,
                icon = Icons.Default.Info
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ImcApp_kt2Theme {
        App()
    }
}