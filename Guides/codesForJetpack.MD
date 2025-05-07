# Jetpack Compose - criando uma tela de login

Veja a solução final no projeto `03-1-login_kt2`.

- Adicionar container para itens em uma coluna 

```kotlin
Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
```

- Adicionar app bar e renomear corpo


```kotlin
package com.example.login_kt2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login_kt2.ui.theme.Login_kt2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Login_kt2Theme {
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
                    Body(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    val name = "Android"

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {}) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Login_kt2Theme {
        Body()
    }
}

```

- Criar o componente App

```kotlin
package com.example.login_kt2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login_kt2.ui.theme.Login_kt2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Login_kt2Theme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
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
        Body(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    val name = "Android"

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {}) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Login_kt2Theme {
        App()
    }
}
```

- Separar o app e body em arquivos diferentes e testar um counter

```kotlin
package com.example.login_kt2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login_kt2.ui.theme.Login_kt2Theme

@Composable
fun Body(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "So far, you clicked $counter times!",
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            counter++
        }) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    Login_kt2Theme {
        Body()
    }
}
```

- Refatorar o Body para ser a LoginScreen

```kotlin
package com.example.login_kt2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login_kt2.ui.theme.Login_kt2Theme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val onLoginButtonClicked : () -> Unit = {
        Log.i("LoginScreen", "name: $name, password: $password")
    }

    val onClearButtonClicked : () -> Unit = {
        name = ""
        password = ""
    }

    Column(
        modifier = modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("User name") }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            visualTransformation =PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onLoginButtonClicked) {
                Text("Login")
            }
            Button(onClick = onClearButtonClicked) {
                Text("Clear")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    Login_kt2Theme {
        LoginScreen()
    }
}
```

- Adicionar uma ViewModel

```yaml
# arquivo libs.versions.toml
[versions]
lifecycle = "2.6.2" # Or latest

[libraries]
androidx-lifecycle-viewmodel-compose = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-compose", version.ref = "lifecycle" }

# build.gradle.kts

implementation(libs.androidx.lifecycle.viewmodel.compose)
```

```kotlin
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
fun LoginScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
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

//MainViewModel.kt
package com.example.login_kt2

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_kt2.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var username by mutableStateOf("")
    var usernameError by mutableStateOf(false)
    var usernameErrorMessage by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordError by mutableStateOf(false)
    var passwordErrorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    val userRepository = UserRepository()

    fun performLogin() {
        usernameError = false
        passwordError = false

        username = username.trim()
        if (username == "") {
            usernameError = true
            usernameErrorMessage = "empty"
            return
        }

        if (password == "") {
            passwordError = true
            passwordErrorMessage = "empty"
            return
        }

        viewModelScope.launch {
            isLoading = true
            val status = userRepository.login(username, password)
            isLoading = false
            Log.i("MainViewModel", "name: $username, password: $password, status: $status")
            when(status) {
                "success" -> {
                    Log.i("MainViewModel", "moving to main screen")
                }
                "wrong_username" -> {
                    usernameError = true
                    usernameErrorMessage = "wrong user name"
                }
                "wrong_password" -> {
                    passwordError = true
                    passwordErrorMessage = "wrong password"
                }
                else -> {
                    usernameError = true
                    usernameErrorMessage = "Error: $status"
                }
            }
        }
    }

    fun clearLogin() {
        username = ""
        password = ""
        usernameError = false
        passwordError = false
    }
}

// UserRepository.kt
package com.example.login_kt2.repository

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun login(username : String, password : String) : String {
        delay(2000)

        return when {
            !listOf("user1", "user2").contains(username) -> "wrong_username"
            username == "user1" && password != "123" -> "wrong_password"
            username == "user1" && password == "123" -> "success"
            else -> "failed_connection"
        }
    }
}
```

- Fazer a transição para uma nova tela. Adicione o código a seguir e peça para o Android Studio resolver o problema das dependências.

```kotlin
package com.example.login_kt2

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.login_kt2.ui.theme.Login_kt2Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun App(navController : NavHostController = rememberNavController()) {
    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable("login") {
                LoginScreen()
            }
        }
    }
}

```

```kotlin
//MainScreen.kt
package com.example.login_kt2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login_kt2.ui.theme.Login_kt2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    navigateUp : () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Main") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
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
                "Main Page",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {}) {
                Text("Logout")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Login_kt2Theme {
        MainScreen()
    }
}
```

- Ao final:

```kotlin
// App.kt
package com.example.login_kt2

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.login_kt2.ui.theme.Login_kt2Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun App(navController : NavHostController = rememberNavController()) {
    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable("login") {
                LoginScreen(onSuccessfulLogin = {
                    navController.navigate("main")
                })
            }
            composable("main") {
                MainScreen(
                    navigateUp = {
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
    Login_kt2Theme {
        App()
    }
}

// MainViewModel.kt
package com.example.login_kt2

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_kt2.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var username by mutableStateOf("")
    var usernameError by mutableStateOf(false)
    var usernameErrorMessage by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordError by mutableStateOf(false)
    var passwordErrorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isLoginSuccessful by mutableStateOf(false)

    val userRepository = UserRepository()

    fun performLogin() {
        usernameError = false
        passwordError = false

        username = username.trim()
        if (username == "") {
            usernameError = true
            usernameErrorMessage = "empty"
            return
        }

        if (password == "") {
            passwordError = true
            passwordErrorMessage = "empty"
            return
        }

        viewModelScope.launch {
            isLoading = true
            val status = userRepository.login(username, password)
            isLoading = false
            Log.i("MainViewModel", "name: $username, password: $password, status: $status")
            when(status) {
                "success" -> {
                    Log.i("MainViewModel", "moving to main screen")
                    isLoginSuccessful = true
                }
                "wrong_username" -> {
                    usernameError = true
                    usernameErrorMessage = "wrong user name"
                }
                "wrong_password" -> {
                    passwordError = true
                    passwordErrorMessage = "wrong password"
                }
                else -> {
                    usernameError = true
                    usernameErrorMessage = "Error: $status"
                }
            }
        }
    }

    fun clearLogin() {
        username = ""
        password = ""
        usernameError = false
        passwordError = false
    }

    fun notifyTransition() {
        isLoginSuccessful = false
        clearLogin()
    }
}
```

- Excluir a página de login da back stack:

```kotlin
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
    // here
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startingRoute
        ) {
            composable("login") {
                LoginScreen(onSuccessfulLogin = {
                    // here
                    navController.navigate("main") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                })
            }
            composable("main") {
                MainScreen(
                    navigateUp = {
                        // here
                        scope.launch {
                            activity?.finish()
                        }
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
```