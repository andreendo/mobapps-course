import 'package:flutter/material.dart';

class MyLogo extends StatelessWidget {

  const MyLogo({super.key});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Center(
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Image.asset(
            "assets/images/composelogo.png",
            fit: BoxFit.cover,
          ),
        ),
      ),
    );
  }
}