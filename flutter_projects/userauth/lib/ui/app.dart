import 'package:flutter/material.dart';
import 'package:userauth/ui/login/widgets/login_screen.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Users Auth and Items',
      theme: ThemeData(
        colorScheme: .fromSeed(seedColor: Colors.blueGrey),
      ),
      home: const LoginScreen(title: 'Flutter Users Auth and Items'),
    );
  }
}