
import 'package:flutter/material.dart';
import 'package:userauth/data/repositories/token_repository.dart';
import 'package:userauth/data/repositories/user_repository.dart';

class LoginViewModel extends ChangeNotifier {

  final UserRepository _userRepository;
  final TokenRepository _tokenRepository;

  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  String? errorMessage = null;
  bool isLoggedIn = false;

  LoginViewModel(this._userRepository, this._tokenRepository);

  Future<void> clickLogin() async {
    isLoggedIn = false;
    errorMessage = null;
    print("Username: ${usernameController.text}, password: ${passwordController.text}");

    if (usernameController.text.isEmpty || passwordController.text.isEmpty) {
      errorMessage = "Fill user name and password!";
      notifyListeners();
      return;
    }

    try {
      final token = await _userRepository.login(usernameController.text, passwordController.text);
      await _tokenRepository.saveToken(token);
      await _tokenRepository.saveUsername(usernameController.text);
      isLoggedIn = true;
    } catch(e) {
      errorMessage = e.toString();
    } finally {
      notifyListeners();
    }
  }
}