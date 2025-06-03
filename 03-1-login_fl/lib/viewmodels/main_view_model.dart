import 'package:flutter/material.dart';
import 'package:login_fl/repository/floor/login_try.dart';
import 'package:login_fl/repository/login_try_repository.dart';
import 'package:login_fl/repository/user_repository.dart';
import 'package:login_fl/ui/login/form_error.dart';

class MainViewModel extends ChangeNotifier {

  final UserRepository userRepository;
  final LoginTryRepository loginTryRepository;

  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  FormError _usernameError = FormError.none;
  FormError _passwordError = FormError.none;
  String? _customError;

  bool _isLoading = false;

  MainViewModel(this.userRepository, this.loginTryRepository);

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

    // saving the login try
    final loginTry = LoginTry(
        username: usernameController.text,
        passwordLength: passwordController.text.length,
        wasSuccessful: status == "success",
        whenTried: DateTime.now().toString()
    );
    loginTryRepository.insert(loginTry);

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