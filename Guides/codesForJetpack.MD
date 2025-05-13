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

### Refatorar para conectar com uma web API

- No projeto `06-2-boardService` execute os comandos a seguir; precisa do Nodejs instalado.

```bash
>> npm install
>> npm run start_login
```

- Adicione ao AndroidManifest.xml os elementos a seguir:

```xml
<!-- adicione o parâmetro android:usesCleartextTraffic="true" para aceitar conexões HTTP -->
<application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:usesCleartextTraffic="true"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Login_kt2"
        tools:targetApi="31">

<uses-permission android:name="android.permission.INTERNET"/>
```

- No build.gradle.kts, adicione as dependências a seguir para o retrofit.

```kotlin
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
```

- Adicione as classes usando a biblioteca Retrofit:

```kotlin
// UserData.kt
package com.example.login_kt2.repository.retrofit

data class UserData (
    val username : String,
    val password : String
)

//ResponseData.kt
package com.example.login_kt2.repository.retrofit

data class ResponseData(
    val message: String
)

//LoginApiInterface.kt
package com.example.login_kt2.repository.retrofit

import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApiInterface {

    @POST("login")
    suspend fun login(@Body userData: UserData) : ResponseData

}

//RetrofitInstance.kt
package com.example.login_kt2.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// objeto singleton
object RetrofitInstance {
    private
    const val BASE_URL = "http://10.0.2.2:3001/"
    const val TO_TEST_BASE_URL = "http://localhost:3001/"

    val api : LoginApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApiInterface::class.java)
    }

    val testapi : LoginApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TO_TEST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApiInterface::class.java)
    }
}
```

- Refatorar a classe UserRepository para chamar a Web API:

```kotlin
class UserRepository(val useTestUrl: Boolean = false) {

    private var client: LoginApiInterface

    init {
        client = if (useTestUrl) RetrofitInstance.testapi else RetrofitInstance.api
    }

    suspend fun login(username : String, password : String) : String {
        try {
            val res = client.login(UserData(username, password))
            return when(res.message) {
                "success" -> "success"
                "unexisting username" -> "wrong_username"
                "wrong password" -> "wrong_password"
                else -> res.message
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "failed_connection"
        }
    }
    // ...
}

```

- Adicione testes para a conexão via Retrofit (evita precisar realizar testes via UI).

```kotlin
package com.example.login_kt2.repository

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class UserRepositoryTest {

    val userRepository = UserRepository(true)

    @Test
    fun loginSuccessful() = runBlocking {
        val res = userRepository.login("user1", "123")
        assertEquals("success", res)
    }

    @Test
    fun loginWrongUsername() = runBlocking {
        val res = userRepository.login("andre", "123")
        assertEquals("wrong_username", res)
    }

    @Test
    fun loginWrongPassword() = runBlocking {
        val res = userRepository.login("user1", "123wrong")
        assertEquals("wrong_password", res)
    }
}
```

### Salvar localmente todas as tentativas de login, com sucesso ou não

- No build.gradle.kts, adicionar as seguir mudanças para usar o Android Room:

```kotlin
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")  // esta linha
}

// ...

dependencies {

    // Room dependencies
    // it needs kapt plugin -> id("kotlin-kapt")
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("androidx.room:room-testing:$room_version")
    androidTestImplementation("app.cash.turbine:turbine:0.9.0")

}

```

- Adicione classes associadas ao Android Room:

```kotlin
// LoginTry.kt
package com.example.login_kt2.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginTry(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val username : String,
    val passwordLength : Int,
    val wasSuccessful : Boolean,
    val whenTried : String
)

// LoginTryDao.kt
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginTryDao {

    @Insert
    suspend fun insert(loginTry : LoginTry)

    @Query("Select * from LoginTry Order by id DESC")
    suspend fun getAll() : List<LoginTry>

    @Query("Select * from LoginTry Order by id DESC")
    fun getAllInFlow() : Flow<List<LoginTry>>

}

// AppDatabase.kt
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoginTry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginTryDao(): LoginTryDao

    companion object {

        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}


// LoginTryRepository

import com.example.login_kt2.repository.room.LoginTry
import com.example.login_kt2.repository.room.LoginTryDao
import kotlinx.coroutines.flow.Flow

class LoginTryRepository(private val dao: LoginTryDao) {

    suspend fun insert(loginTry: LoginTry) = dao.insert(loginTry)

    fun getAll() : Flow<List<LoginTry>> = dao.getAllInFlow()

}
```

- Adicione as classes a seguir para facilitar a criação das viewModels e do repositories 

```kotlin
// AppApplication.kt

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.login_kt2.repository.LoginTryRepository
import com.example.login_kt2.repository.room.AppDatabase

/**
 * REQUIREMENT:
 * You need to specify attribute android:name=".AppApplication" in AndroidManifest.xml
 * Otherwise, this class is not initialized
 */
class AppApplication : Application() {

    // instance to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        Log.i("AppApplication", "onCreate")
    }
}

/**
 * We want to limit the visibility of Android-related objects to ViewModels and Composable.
 *
 * So, we attach here the repositories to a GameApplication object
 *    so that we can retrieve them in the AppViewModelProvider.
 */
class AppContainer(private val context: Context) {
    val loginTryRepository : LoginTryRepository by lazy {
        LoginTryRepository(AppDatabase.getDatabase(context).loginTryDao())
    }
}


// AppViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


/**
 * Provides Factory to create instance of ViewModel for the entire app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for MainViewModel
        initializer {
            MainViewModel(appApplication().container.loginTryRepository)
        }

        // Initializer for LogTriesViewModel
        initializer {
            LogTriesViewModel(appApplication().container.loginTryRepository)
        }

    }
}

fun CreationExtras.appApplication(): AppApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)

```

- Refatore a mainViewModel para gravar tentativas de login. Adicione uma nova screen e viewModel para mostrar o log de tentativas.

```kotlin
// LogTriesViewModel.kt
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_kt2.repository.LoginTryRepository
import com.example.login_kt2.repository.room.LoginTry
import kotlinx.coroutines.launch

class LogTriesViewModel(val loginTryRepository: LoginTryRepository) : ViewModel() {

    var loginTries = mutableStateListOf<LoginTry>()

    init {
        viewModelScope.launch {
            loginTryRepository.getAll().collect {
                loginTries.clear()
                it.forEach { v -> loginTries.add(v) }
            }
        }
    }

}

// LogTriesScreen.kt
package com.example.login_kt2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogTriesScreen(
    navigateUp : () -> Unit = {},
    viewModel: LogTriesViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Log Tries Screen") },
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
            modifier = Modifier
                .padding(innerPadding) // Apply the innerPadding to the Column (avoid topbar on top of items)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Login tries so far: ${viewModel.loginTries.size}")
            LazyColumn {
                itemsIndexed(viewModel.loginTries) { index, item ->
                    LoginTryCard(item.toString())
                }
            }
        }
    }
}

// LoginTryCard.kt
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun LoginTryCard(item: String) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp) // Add padding inside the card
        )
    }
}
```