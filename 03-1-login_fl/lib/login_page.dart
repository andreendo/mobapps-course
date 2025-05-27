import 'package:flutter/material.dart';
import 'package:login_fl/loading_dialog.dart';
import 'package:login_fl/main_page.dart';
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

  void onLoginSuccess() {
    // use pushReplacement para remover o atual
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => MainPage()
      )
    );
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
                  onPressed: () => viewModel.performLogin(onLoginSuccess),
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