package com.example.imcapp_kt2

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun App(
    imcViewModel: ImcViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val activity = (LocalContext.current as? Activity)

    // state holder to identify which screen came before ScreenOne
    var screenBeforeScreenOne by remember {
        mutableStateOf("")
    }
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