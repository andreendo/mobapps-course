package com.example.actionbarapp_kt2

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.actionbarapp_kt2.ui.theme.ActionBarApp_kt2Theme


@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    // keep a ref to the current entry(route) in the stack
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    // helper functions
    val showToast = { message: String ->
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    val navigateTo = { route: String ->
        if (route == navBackStackEntry?.destination?.route) {
            showToast("You are already in $route !!")
        } else {
            navController.navigate(route)
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                onItem1Clicked = { navigateTo("second") },
                onItem2Clicked = { navigateTo("third") },
                onSettingsClicked = { showToast("Clicked in settings") },
                onFavoriteClicked = { showToast("Clicked in Favorites") }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "first",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("first") {
                First(
                    onNext1Clicked = { navigateTo("second") },
                    onNext2Clicked = { navigateTo("third") }
                )
            }
            composable("second") {
                Second(
                    onPreviousButtonClicked = { navController.navigateUp() },
                    onCloseButtonClicked = {
                        // it navigates to 'first' (in the stack) and pop everything above
                        navController.popBackStack("first", false)
                    }
                )
            }
            composable("third") {
                Third(
                    onPreviousButtonClicked = { navController.navigateUp() },
                    onCloseButtonClicked = {
                        navController.popBackStack("first", false)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ActionBarApp_kt2Theme {
        App()
    }
}

