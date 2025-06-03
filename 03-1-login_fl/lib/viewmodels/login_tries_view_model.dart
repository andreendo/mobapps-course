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