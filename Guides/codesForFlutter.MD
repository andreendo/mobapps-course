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

### Salvar localmente todas as tentativas de login, com sucesso ou não


- Executar os comandos a seguir:

```bash
dart pub add floor

dart pub add floor_generator --dev

dart pub add build_runner --dev

```

- Adicione classes associadas a biblioteca Floor:

```dart
// login_try.dart


import 'package:floor/floor.dart';

@entity
class LoginTry {
  @PrimaryKey(autoGenerate: true)
  int? id;

  final String username;

  final int passwordLength;

  final bool wasSuccessful;

  final String whenTried;

  LoginTry({this.id,
    required this.username,
    required this.passwordLength,
    required this.wasSuccessful,
    required this.whenTried});

  @override
  String toString() {
    return 'LoginTry(id: $id, username: $username, passwordLength: $passwordLength, wasSuccessful: $wasSuccessful, whenTried: $whenTried)';
  }
}

// login_try_dao.dart
import 'package:floor/floor.dart';
import 'package:login_fl/repository/floor/login_try.dart';

@dao
abstract class LoginTryDao {

  @Insert(onConflict: OnConflictStrategy.abort)
  Future<void> insert(LoginTry loginTry);

  @Query('SELECT * FROM LoginTry ORDER BY id DESC')
  Future<List<LoginTry>> getAll();

  @Query('SELECT * FROM LoginTry ORDER BY id DESC')
  Stream<List<LoginTry>> getAllInStream();

}

// app_database.dart

// imports needed for the floor classes
import 'package:floor/floor.dart';
import 'dart:async';
import 'package:sqflite/sqflite.dart' as sqflite;

import 'package:login_fl/repository/floor/login_try_dao.dart';
import 'package:login_fl/repository/floor/login_try.dart';

part 'app_database.g.dart';

@Database(version: 1, entities: [LoginTry])
abstract class AppDatabase extends FloorDatabase {

  LoginTryDao get loginTryDao;

}

// login_try_repository.dart

import 'package:login_fl/repository/floor/login_try.dart';
import 'package:login_fl/repository/floor/login_try_dao.dart';

class LoginTryRepository {

  final LoginTryDao loginTryDao;

  LoginTryRepository(this.loginTryDao);

  Future<void> insert(LoginTry loginTry) => loginTryDao.insert(loginTry);

  Future<List<LoginTry>> getAll() => loginTryDao.getAll();

  Stream<List<LoginTry>> getAllInStream() => loginTryDao.getAllInStream();
}

```

- Executar o comando `dart run build_runner build` para gerar o código que fazer a conexão com o SQLite. 

- Refatorar a main() para inicializar as classes de persistência. 

```dart
import 'package:flutter/material.dart';
import 'package:login_fl/repository/floor/app_database.dart';
import 'package:login_fl/repository/login_try_repository.dart';
import 'package:login_fl/ui/app.dart';
import 'package:login_fl/viewmodels/main_view_model.dart';
import 'package:login_fl/repository/user_repository.dart';
import 'package:provider/provider.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // init repositories
  final database = await $FloorAppDatabase.databaseBuilder('app_database.db').build();
  final loginTryRepository = LoginTryRepository(database.loginTryDao);
  final userRepository = UserRepository();

  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => MainViewModel(userRepository, loginTryRepository)),
        ChangeNotifierProvider(create: (_) => LoginTriesViewModel(loginTryRepository))
      ],
      child: const App(),
    )
  );
}
```

- Adicionar testes para verificar se a persistência está funcionando adequadamente. 

```dart
import 'package:flutter_test/flutter_test.dart';
import 'package:login_fl/repository/floor/app_database.dart';
import 'package:login_fl/repository/floor/login_try.dart';
import 'package:login_fl/repository/login_try_repository.dart';


void main() {
  late LoginTryRepository loginTryRepository;

  setUp(() async {
    // this test needs to have SQLite installed in your system
    // >> sudo apt install libsqlite3-dev
    final database = await $FloorAppDatabase
        .inMemoryDatabaseBuilder() // 💡 Use in-memory DB for tests
        .build();

    loginTryRepository = LoginTryRepository(database.loginTryDao);
  });

  test('Insert one try', () async {
    final loginTry = LoginTry(username: 'username',
        passwordLength: 5,
        wasSuccessful: false,
        whenTried: 'some day');
    await loginTryRepository.insert(loginTry);
    final list = await loginTryRepository.getAll();
    expect(list.length, equals(1));
    expect(list[0].username, equals(loginTry.username));
  });
}
```

- Refatore a mainViewModel para gravar tentativas de login. Adicione uma nova screen e viewModel para mostrar o log de tentativas.

```dart
// main_view_model.dart
    // ...
    if (status == "wrong_username") {
      _usernameError = FormError.wrongUsername;
    } else if (status == "wrong_password") {
      _passwordError = FormError.wrongPassword;
    } else {
      _usernameError = FormError.custom;
      _customError = "Error: $status";
    }

    // saving the login try
    final loginTry = LoginTry(
        username: usernameController.text,
        passwordLength: passwordController.text.length,
        wasSuccessful: status == "success",
        whenTried: DateTime.now().toString()
    );
    loginTryRepository.insert(loginTry);

    // ...

// login_tries_view_model.dart´
import 'package:flutter/material.dart';
import 'package:login_fl/repository/floor/login_try.dart';
import 'package:login_fl/repository/login_try_repository.dart';

class LoginTriesViewModel extends ChangeNotifier {

  final LoginTryRepository loginTryRepository;

  List<LoginTry> _loginTries = [];

  get loginTries => _loginTries;

  LoginTriesViewModel(this.loginTryRepository) {
    initialize();
  }

  void initialize() async {
    loginTryRepository.getAllInStream().listen((List<LoginTry> tries) {
      _loginTries = tries;
      notifyListeners();
    });
  }
}

// login_tries_page.dart

import 'package:flutter/material.dart';
import 'package:login_fl/l10n/app_localizations.dart';
import 'package:login_fl/viewmodels/login_tries_view_model.dart';
import 'package:provider/provider.dart';

class LoginTriesPage extends StatelessWidget {
  const LoginTriesPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(AppLocalizations.of(context)!.login_tries_page),
      ),
      body: Consumer<LoginTriesViewModel>(
        builder: (context, viewmodel, child) {
          return Center(
            child: Column(
                children: [
                  SizedBox(height: 10,),
                  Text(
                      style: Theme.of(context).textTheme.headlineSmall,
                      '${AppLocalizations.of(context)!.login_tries_so_far} ${viewmodel.loginTries.length}'
                  ),
                  SizedBox(height: 10,),
                  Expanded(
                    child: ListView.builder(
                      itemCount: viewmodel.loginTries.length,
                      itemBuilder: (context, index) {
                        return TryCard(text: viewmodel.loginTries[index].toString());
                      }
                    )
                  ),
                ]
            ),
          );
        }
      )
    );
  }
}

class TryCard extends StatelessWidget {
  final String text;

  const TryCard({super.key, required this.text});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        border: Border.all(color: Colors.black38),
      ),
      child: ListTile(
        title: Text(text),
      ),
    );
  }
}

```