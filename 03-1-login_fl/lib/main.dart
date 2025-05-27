import 'package:flutter/material.dart';
import 'package:login_fl/ui/app.dart';
import 'package:login_fl/viewmodels/main_view_model.dart';
import 'package:login_fl/repository/user_repository.dart';
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