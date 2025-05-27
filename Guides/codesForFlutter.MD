# Flutter - criando uma tela de login

Veja a solução final no projeto `?`.

- Separar em arquivos App, e MyHomePage (renomeando para LoginPage). Modifique para incluir um button e testar um counter.

```dart
import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  // state holders
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
    print("clicked $_counter");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Login Page"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
              style: Theme.of(context).textTheme.bodyLarge,
            ),
            SizedBox(height: 10),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            SizedBox(height: 10),
            FilledButton(
              onPressed: _incrementCounter,
              child: Text('Click Me'),
            )
          ],
        ),
      ),
    );
  }
}
```

- Refatorar a classe LoginPage para incluir os campos e botões.

```dart
import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {

  // simplify state holding of text fields
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();

  void _onLoginClicked() {
    print("Login button clicked");
    print("Username: ${_usernameController.text}, password: ${_passwordController.text}");
  }

  void _onClearClicked() {
    print("Clear button clicked");
    _usernameController.clear();
    _passwordController.clear();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Login Page"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextFormField(
              controller: _usernameController,
              decoration: InputDecoration(
                labelText: 'Username',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.person)
              ),
            ),
            SizedBox(height: 10),
            TextFormField(
              controller: _passwordController,
              obscureText: true,
              decoration: InputDecoration(
                labelText: 'Password',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.lock)
              ),
            ),
            SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                FilledButton(
                  onPressed: _onLoginClicked,
                  child: Text('Login'),
                ),
                SizedBox(width: 20),
                FilledButton(
                  onPressed: _onClearClicked,
                  child: Text('Clear'),
                ),
              ],
            )

          ],
        ),
      ),
    );
  }
}
```

- Adicionar uma ViewModel. Executar comando `dart pub add provider`.


- Criar as classes repository e viewModel.

```dart
// user_repository.dart
class UserRepository {

  Future<String> login(String username, String password) async {
    await Future.delayed(const Duration(seconds: 2));

    if (!['user1', 'user2'].contains(username)) {
      return 'wrong_username';
    } else if (username == 'user1' && password != '123') {
      return 'wrong_password';
    } else if (username == 'user1' && password == '123') {
      return 'success';
    } else {
      return 'failed_connection';
    }
  }

}


// main_view_model.dart
import 'package:flutter/material.dart';
import 'package:login_fl/user_repository.dart';

class MainViewModel extends ChangeNotifier {

  final UserRepository userRepository;

  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  String? _usernameError;
  String? _passwordError;

  bool _isLoading = false;

  MainViewModel(this.userRepository);

  String? get usernameError => _usernameError;
  String? get passwordError => _passwordError;
  bool get isLoading => _isLoading;

  Future<void> performLogin() async {
    print("Login button clicked");
    print("Username: ${usernameController.text}, password: ${passwordController.text}");

    _usernameError = null;
    _passwordError = null;

    if (usernameController.text == "") {
      _usernameError = "empty";
      notifyListeners();
      return;
    }

    if (passwordController.text == "") {
      _passwordError = "empty";
      notifyListeners();
      return;
    }

    _isLoading = true;
    notifyListeners();

    var status = await userRepository.login(usernameController.text, passwordController.text);

    if (status == "success") {
      print("MainViewModel: moving to main screen");
      // You might also navigate to a new screen here:
    } else if (status == "wrong_username") {
      _usernameError = "wrong user name";
    } else if (status == "wrong_password") {
      _passwordError = "wrong password";
    } else {
      _usernameError = "Error: $status";
    }

    _isLoading = false;
    notifyListeners();
  }

  void clearLogin() {
    print("Clear button clicked");
    usernameController.clear();
    passwordController.clear();
    _usernameError = null;
    _passwordError = null;
    notifyListeners();
  }
}
```

- Modificar `main.dart` para incluir o provider da viewModel:

```dart
import 'package:flutter/material.dart';
import 'package:login_fl/app.dart';
import 'package:login_fl/main_view_model.dart';
import 'package:login_fl/user_repository.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => MainViewModel(UserRepository()))
      ],
      child: const App(),
    )
  );
}
```

- Refatorar a login page:


```dart
import 'package:flutter/material.dart';
import 'package:login_fl/loading_dialog.dart';
import 'package:login_fl/main_view_model.dart';
import 'package:provider/provider.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {

  @override
  void initState() {
    super.initState();

    MainViewModel viewModel = Provider.of<MainViewModel>(context, listen: false);
    viewModel.addListener(() {
      if (viewModel.isLoading) {
        showDialog(
            context: context,
            barrierDismissible: false,
            builder: (_) => LoadingDialog()
        );
      } else {
        if (Navigator.of(context).canPop()) {
          Navigator.of(context).pop();
        }
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<MainViewModel>(context);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Login Page"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextFormField(
              controller: viewModel.usernameController,
              decoration: InputDecoration(
                labelText: 'Username',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.person),
                errorText: viewModel.usernameError
              ),
            ),
            SizedBox(height: 10),
            TextFormField(
              controller: viewModel.passwordController,
              obscureText: true,
              decoration: InputDecoration(
                labelText: 'Password',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.lock),
                errorText: viewModel.passwordError
              ),
            ),
            SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                FilledButton(
                  onPressed: () => viewModel.performLogin(),
                  child: Text('Login'),
                ),
                SizedBox(width: 20),
                FilledButton(
                  onPressed: () => viewModel.clearLogin(),
                  child: Text('Clear'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
```




- Fazer a transição para uma nova tela. Criar uma nova tela. 

```dart
// main_page.dart
import 'package:flutter/material.dart';

class MainPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Main Page"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Some text here'),
            FilledButton(
                onPressed: () {}, 
                child: Text('Logout'))
          ],
        ),
      ),
    );
  }
}
```

- Refatorar a viewModel e a login page para implementar a transição em caso de sucesso no login.

```dart
// main_view_model.dart

  Future<void> performLogin(void Function() onSuccess) async {
    print("Login button clicked");
    print("Username: ${usernameController.text}, password: ${passwordController
        .text}");

    _usernameError = null;
    _passwordError = null;

    if (usernameController.text == "") {
      _usernameError = "empty";
      notifyListeners();
      return;
    }

    if (passwordController.text == "") {
      _passwordError = "empty";
      notifyListeners();
      return;
    }

    _isLoading = true;
    notifyListeners();

    var status = await userRepository.login(
        usernameController.text, passwordController.text);

    if (status == "wrong_username") {
      _usernameError = "wrong user name";
    } else if (status == "wrong_password") {
      _passwordError = "wrong password";
    } else {
      _usernameError = "Error: $status";
    }
    _isLoading = false;
    notifyListeners();

    if (status == "success") {
      print("MainViewModel: moving to main screen");
      onSuccess();
    }

// login_page.dart

  void onLoginSuccess() {
    // use pushReplacement(..) para remover o atual
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => MainPage()
      )
    );
  }

  //...

                FilledButton(
                  onPressed: () => viewModel.performLogin(onLoginSuccess),
                  child: Text('Login'),
                ),

```


### Refatorar para conectar com uma web API

- No projeto `06-2-boardService` execute os comandos a seguir; precisa do Nodejs instalado.

```bash
>> npm install
>> npm run start_login
```

- Adicione a biblioteca Dio `dart pub add dio`. 

- Adicionar as classes que representam a entrada e saída da API:

```dart
// user_data.dart
class UserData {
  final String username;
  final String password;

  UserData({
    required this.username,
    required this.password
  });

  Map<String, dynamic> toJson() {
    return {
      'username' : username,
      'password' : password
    };
  }
}

// response_data.dart 
class ResponseData {
  final String message;

  ResponseData({
    required this.message
  });

  factory ResponseData.fromJson(Map<String, dynamic> json) {
    return ResponseData(message: json['message']);
  }
}
```

- Refatorar a classe UserRepository para chamar a Web API:

```dart
// user_repository.dart

import 'package:dio/dio.dart';
import 'package:login_fl/repository/response_data.dart';
import 'package:login_fl/repository/user_data.dart';

class UserRepository {

  static const _BASE_URL = "http://10.0.2.2:3001";
  static const _TO_TEST_URL = "http://localhost:3001";

  late final Dio _dio;

  UserRepository([bool test = false]) {
    if (test) {
      _dio = Dio(BaseOptions(baseUrl: _TO_TEST_URL));
    } else {
      _dio = Dio(BaseOptions(baseUrl: _BASE_URL));
    }
  }

  Future<String> login(String username, String password) async {
    try {
      final userData = UserData(username: username, password: password);
      final response = await _dio.post(
        '/login',
        data: userData.toJson()
      );
      if (response.statusCode == 200 || response.statusCode == 201) {
        final responseData = ResponseData.fromJson(response.data);
        if (responseData.message == 'success') {
          return 'success';
        } else if (responseData.message == 'unexisting username') {
          return 'wrong_username';
        } else if (responseData.message == 'wrong password') {
          return 'wrong_password';
        } else {
          return responseData.message;
        }
      } else {
        throw Exception('failed status code');
      }
    } catch(e) {
      print(e);
      return 'failed_connection';
    }
  }

}
```

- Adicione testes para a conexão via Dio (evita realizar testes manuais via UI). Adicionar a biblioteca de testes `dart pub add test --dev`. Em seguida, implemente alguns testes.

```dart
import 'package:test/test.dart';
import 'package:login_fl/repository/user_repository.dart';

// run tests, >> dart test -r expanded

void main() {
  final userRepository = UserRepository(true);

  test('login successful', () async {
    final response = await userRepository.login('user1', '123');
    expect(response, equals('success'));
  });

  test('login wrong user', () async {
    final response = await userRepository.login('user5', '123');
    expect(response, equals('wrong_username'));
  });

  test('login wrong password', () async {
    final response = await userRepository.login('user1', '456');
    expect(response, equals('wrong_password'));
  });
}
```

### Adicionar internacionalização

- Executar os comandos a seguir para adicionar as bibliotecas: `dart pub add flutter_localizations --sdk=flutter`, ` dart pub add intl:any`.

- Alterar o `pubspec.yaml` e criar o arquivo `l10n.yaml`: 

```yaml
# pubspec.yaml
flutter:
  uses-material-design: true
  generate: true # esta linha


# l10n.yaml
arb-dir: lib/l10n
template-arb-file: app_en.arb
output-localization-file: app_localizations.dart
```

- Na pasta `lib/l10n`, adicionar os arquivos:

```json
// app_en.arb
{
  "login_page": "Login Page",
  "username": "Username",
  "password": "Password",
  "login": "Login",
  "clear": "Clear",
  "empty": "Empty",
  "wrong_username": "Wrong user name",
  "wrong_password": "Wrong password",
  "main_page": "Main Page",
  "logout": "Logout",
  "some_text_here": "Some text here!",
  "loading" : "Loading..."
}

// app_pt.arb
{
  "login_page": "Página de Login",
  "username": "Usuário",
  "password": "Senha",
  "login": "Logar",
  "clear": "Limpar",
  "empty": "Vazio",
  "wrong_username": "Usuário incorreto",
  "wrong_password": "Senha incorreta",
  "main_page": "Página Principal",
  "logout": "Logout",
  "some_text_here": "Algum texto aqui!",
  "loading" : "Carregando..."
}
```

- Refatorar a classe `App` para adicionar as configurações de localização:

```dart
import 'package:flutter/material.dart';
import 'package:login_fl/ui/login/login_page.dart';

import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:login_fl/l10n/app_localizations.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Login',
      localizationsDelegates: [
        AppLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate
      ],
      supportedLocales: [
        Locale('en'),
        Locale('pt')
      ],
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const LoginPage(),
    );
  }
}
```

- Refatorar todas as classes View. As maiores modificações em `LoginPage` e `MainViewModel` são mostradas a seguir:

```dart
// form_error.dart
enum FormError {
  none,
  emptyField,
  wrongUsername,
  wrongPassword,
  custom
}

// main_view_model.dart
import 'package:flutter/material.dart';
import 'package:login_fl/repository/user_repository.dart';
import 'package:login_fl/ui/login/form_error.dart';

class MainViewModel extends ChangeNotifier {

  final UserRepository userRepository;

  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  FormError _usernameError = FormError.none;
  FormError _passwordError = FormError.none;
  String? _customError;

  bool _isLoading = false;

  MainViewModel(this.userRepository);

  FormError get usernameError => _usernameError;
  FormError get passwordError => _passwordError;
  String? get customError => _customError;
  bool get isLoading => _isLoading;

  Future<void> performLogin(void Function() onSuccess) async {
    print("Login button clicked");
    print("Username: ${usernameController.text}, password: ${passwordController
        .text}");

    _usernameError = FormError.none;
    _passwordError = FormError.none;
    _customError = null;

    if (usernameController.text == "") {
      _usernameError = FormError.emptyField;
      notifyListeners();
      return;
    }

    if (passwordController.text == "") {
      _passwordError = FormError.emptyField;
      notifyListeners();
      return;
    }

    _isLoading = true;
    notifyListeners();

    var status = await userRepository.login(
        usernameController.text, passwordController.text);

    if (status == "wrong_username") {
      _usernameError = FormError.wrongUsername;
    } else if (status == "wrong_password") {
      _passwordError = FormError.wrongPassword;
    } else {
      _usernameError = FormError.custom;
      _customError = "Error: $status";
    }
    _isLoading = false;
    notifyListeners();

    if (status == "success") {
      print("MainViewModel: moving to main screen");
      onSuccess();
    }
  }

  void clearLogin() {
    print("Clear button clicked");
    usernameController.clear();
    passwordController.clear();
    _usernameError = FormError.none;
    _passwordError = FormError.none;
    _customError = null;
    notifyListeners();
  }
}

// login_page.dart
import 'package:flutter/material.dart';
import 'package:login_fl/l10n/app_localizations.dart';
import 'package:login_fl/ui/loading_dialog.dart';
import 'package:login_fl/ui/login/form_error.dart';
import 'package:login_fl/ui/main/main_page.dart';
import 'package:login_fl/viewmodels/main_view_model.dart';
import 'package:provider/provider.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {

  @override
  void initState() {
    super.initState();

    MainViewModel viewModel = Provider.of<MainViewModel>(context, listen: false);
    viewModel.addListener(() {
      if (viewModel.isLoading) {
        showDialog(
            context: context,
            barrierDismissible: false,
            builder: (_) => LoadingDialog()
        );
      } else {
        if (Navigator.of(context).canPop()) {
          Navigator.of(context).pop();
        }
      }
    });
  }

  void onLoginSuccess() {
    // use pushReplacement para remover o atual
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => MainPage()
      )
    );
  }

  String? _getErrorMessage(FormError error, String? custom) {
    final localizations = AppLocalizations.of(context)!;
    switch(error) {
      case FormError.emptyField:
        return localizations.empty;
      case FormError.wrongUsername:
        return localizations.wrong_username;
      case FormError.wrongPassword:
        return localizations.wrong_password;
      case FormError.custom:
        return custom ?? "";
      case FormError.none:
        return null;
    }
  }

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<MainViewModel>(context);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(AppLocalizations.of(context)!.login_page),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextFormField(
              controller: viewModel.usernameController,
              decoration: InputDecoration(
                labelText: AppLocalizations.of(context)!.username,
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.person),
                errorText: _getErrorMessage(viewModel.usernameError, viewModel.customError)
              ),
            ),
            SizedBox(height: 10),
            TextFormField(
              controller: viewModel.passwordController,
              obscureText: true,
              decoration: InputDecoration(
                labelText: AppLocalizations.of(context)!.password,
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.lock),
                errorText: _getErrorMessage(viewModel.passwordError, viewModel.customError)
              ),
            ),
            SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                FilledButton(
                  onPressed: () => viewModel.performLogin(onLoginSuccess),
                  child: Text(AppLocalizations.of(context)!.login),
                ),
                SizedBox(width: 20),
                FilledButton(
                  onPressed: () => viewModel.clearLogin(),
                  child: Text(AppLocalizations.of(context)!.clear),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

```

### Salvar localmente todas as tentativas de login, com sucesso ou não (TODO)

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