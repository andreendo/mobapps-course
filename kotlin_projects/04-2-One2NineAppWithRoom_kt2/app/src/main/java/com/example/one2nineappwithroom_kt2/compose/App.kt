package com.example.one2nineappwithroom_kt2.compose

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
import com.example.one2nineappwithroom_kt2.R
import com.example.one2nineappwithroom_kt2.ui.theme.One2NineAppWithRoom_kt2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val scoreViewModel: ScoreViewModel = viewModel(factory = AppViewModelProvider.Factory)

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
            startDestination = "init",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("init") {
                Init(
                    onStartButtonClicked = {
                        gameViewModel.startGame()
                        navController.navigate("game")
                    },
                    onViewScoresButtonClicked = { navController.navigate("scores") }
                )
            }
            composable("game") {
                Game(
                    gameViewModel,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
            composable("scores") {
                ScoreScreen(
                    scoreViewModel,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    One2NineAppWithRoom_kt2Theme {
        App()
    }
}