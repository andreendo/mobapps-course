package com.example.login_kt2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login_kt2.ui.theme.Login_kt2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onSuccessfulLogin : () -> Unit = {},
    onLogTriesButtonClick : () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Title") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "Provide login details",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = viewModel.username,
                isError = viewModel.usernameError,
                supportingText = {
                    if (viewModel.usernameError) {
                        Text(
                            text = viewModel.usernameErrorMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                onValueChange = {
                    viewModel.username = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("User name") }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                isError = viewModel.passwordError,
                supportingText = {
                    if (viewModel.passwordError) {
                        Text(
                            text = viewModel.passwordErrorMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                visualTransformation =PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = viewModel::performLogin) {
                    Text("Login")
                }
                Button(onClick = viewModel::clearLogin) {
                    Text("Clear")
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = onLogTriesButtonClick) {
                Text("View log of login tries")
            }
        }

        if (viewModel.isLoading) {
            LoadingDialog()
        }

        if (viewModel.isLoginSuccessful) {
            onSuccessfulLogin()
            viewModel.notifyTransition()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    Login_kt2Theme {
        LoginScreen(
        )
    }
}