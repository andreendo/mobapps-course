import 'package:flutter/material.dart';
import 'package:imcapp_fl/imcForm.dart';

class MainPage extends StatelessWidget {
  const MainPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text('IMC App'),
      ),
      body: ImcForm(),
    );
  }
}