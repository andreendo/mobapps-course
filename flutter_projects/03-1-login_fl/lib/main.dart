import 'package:flutter/material.dart';
import 'package:login_fl/repository/floor/app_database.dart';
import 'package:login_fl/repository/login_try_repository.dart';
import 'package:login_fl/ui/app.dart';
import 'package:login_fl/viewmodels/login_tries_view_model.dart';
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