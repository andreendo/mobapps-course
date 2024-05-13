import 'package:flutter/material.dart';
import 'package:flutter3/custom_button.dart';
import 'package:flutter3/my_logo.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

/**
 * For a stateful widget, you override the createState method.
 *   Then, you return an object that inherents from class State.
 *   This object that holds states as private attributes and
 *     has a method build to create the widget.
 */
class MyHomePage extends StatefulWidget {
  // constructor
  const MyHomePage({super.key, required this.title});

  // attribute
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  bool _enabled = false;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  void _enableSomething() {
    setState(() {
      _enabled = !_enabled;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        // MyHomePage.title
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
              style: Theme.of(context).textTheme.headlineMedium,
              textAlign: TextAlign.center,
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headlineLarge,
            ),
            CustomButton(
                label: AppLocalizations.of(context)!.clickHereToo,
                onPressed: _incrementCounter
            ),
            SizedBox(
              height: 20,
            ),
            CustomButton(
              label: "Enable / Disable",
              hasCheck: true,
              marked: _enabled,
              onPressed: _enableSomething,
            ),
            _enabled ? const MyLogo() : const SizedBox(),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
