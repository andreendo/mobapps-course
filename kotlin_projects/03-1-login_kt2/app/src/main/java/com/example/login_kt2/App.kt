package com.example.login_kt2

import android.app.Activity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.login_kt2.ui.theme.Login_kt2Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch

@Composable
fun App(
    navController : NavHostController = rememberNavController(),
    startingRoute : String = "login"
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startingRoute
        ) {
            composable("login") {
                LoginScreen(
                    onSuccessfulLogin = {
                        navController.navigate("main") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    },
                    onLogTriesButtonClick = {
                        navController.navigate("logTries")
                    }
                )
            }
            composable("main") {
                MainScreen(
                    navigateUp = {
                        scope.launch {
                            activity?.finish()
                        }
                    }
                )
            }
            composable("logTries") {
                LogTriesScreen(
                    navigateUp = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Login_kt2Theme {
        App()
    }
}