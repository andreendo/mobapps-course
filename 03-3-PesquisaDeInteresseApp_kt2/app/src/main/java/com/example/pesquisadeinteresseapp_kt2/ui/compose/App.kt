package com.example.pesquisadeinteresseapp_kt2.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pesquisadeinteresseapp_kt2.R
import com.example.pesquisadeinteresseapp_kt2.ui.theme.PesquisaDeInteresseApp_kt2Theme
import com.example.pesquisadeinteresseapp_kt2.viewmodels.MyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController = rememberNavController()) {
    // create a viewModel shared between the screens
    val viewModel = viewModel<MyViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("main") {
                MainScreen(
                    viewModel,
                    onGravarPesquisaClicked = { navController.navigate("next") }
                )
            }
            composable("next") {
                NextScreen(
                    viewModel,
                    onCancelarClicked = { navController.navigateUp() },
                    onFinalizarClicked = {
                        viewModel.gravarPesquisa()
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    PesquisaDeInteresseApp_kt2Theme {
        App()
    }
}