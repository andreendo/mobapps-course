import 'package:flutter/material.dart';
import 'package:login_fl/repository/user_repository.dart';

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