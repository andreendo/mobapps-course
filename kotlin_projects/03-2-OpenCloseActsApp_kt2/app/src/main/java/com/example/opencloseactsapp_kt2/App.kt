package com.example.opencloseactsapp_kt2

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.opencloseactsapp_kt2.ui.theme.OpenCloseActsApp_kt2Theme

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
        title = { Text("OpenClose") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        }
    )
}

@Composable
fun App(navController: NavHostController = rememberNavController()) {

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val activity = (LocalContext.current as? Activity)

    // state holder to identify which screen came before ScreenOne
    var screenBeforeScreenOne by remember {
        mutableStateOf("")
    }

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
                MainScreen(
                    onOpenScreen1Clicked = {
                        screenBeforeScreenOne = "Main"
                        navController.navigate("Screen 1")
                    },
                    onOpenScreen2Clicked = {
                        navController.navigate("Screen 2")
                    },
                    onExitClicked = {
                        activity?.finish()
                    }
                )
            }
            composable("Screen 1") {
                ScreenOne(
                    originScreen = screenBeforeScreenOne,
                    onExitClicked = {
                        navController.navigateUp()
                    }
                )
            }
            composable("Screen 2") {
                ScreenTwo(
                    onOpenScreen1Clicked = {
                        screenBeforeScreenOne = "Screen 2"
                        navController.navigate("Screen 1")
                    },
                    onExitClicked = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenCloseActsApp_kt2Theme {
        App()
    }
}