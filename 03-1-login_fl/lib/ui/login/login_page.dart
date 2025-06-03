import 'package:flutter/material.dart';
import 'package:login_fl/l10n/app_localizations.dart';
import 'package:login_fl/ui/loading_dialog.dart';
import 'package:login_fl/ui/login/form_error.dart';
import 'package:login_fl/ui/main/main_page.dart';
import 'package:login_fl/ui/tries/login_tries_page.dart';
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

  void onClickInLoginTries() {
    Navigator.of(context).push(
      MaterialPageRoute(builder: (_) => LoginTriesPage())
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
            SizedBox(height: 50),
            FilledButton(
              onPressed: onClickInLoginTries,
              child: Text(AppLocalizations.of(context)!.view_log_of_login_tries))
          ],
        ),
      ),
    );
  }
}