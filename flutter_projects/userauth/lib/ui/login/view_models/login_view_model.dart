
import 'package:flutter/material.dart';

class LoginViewModel extends ChangeNotifier {

  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  String? errorMessage = null;
  bool isLoggedIn = false;

  Future<void> clickLogin() async {
    errorMessage = null;
    print("Username: ${usernameController.text}, password: ${passwordController.text}");
    if (usernameController.text.isEmpty || passwordController.text.isEmpty) {
      errorMessage = "Fill user name and password!";
      notifyListeners();
      return;
    }

    isLoggedIn = true;
    notifyListeners();
  }
}