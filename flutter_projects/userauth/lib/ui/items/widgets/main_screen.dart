
import 'package:flutter/material.dart';

class MainScreen extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text('Items'),
      ),
      body: Center(
        child: Padding(
          padding: EdgeInsetsGeometry.all(16.0),
          child: Column(
            mainAxisAlignment: .center,
            children: [
              Text(
                'Main Page',
                style: Theme.of(context).textTheme.headlineLarge,
              ),
              Divider(
                height: 40,
                thickness: 2,
              )
            ],
          ),
        ),
      ),
    );
  }
}