import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Toast and Dialog App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blueGrey),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Toast and Dialog App'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> with WidgetsBindingObserver {
  AppLifecycleState _appLifecycleState = AppLifecycleState.resumed;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance!.addObserver(this);
  }

  @override
  void dispose() {
    WidgetsBinding.instance!.removeObserver(this);
    super.dispose();
  }

  /**
   * Callback when the app is put to background or resumed.
   */
  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    setState(() {
      _appLifecycleState = state;
    });
    print('App lifecycle State: $_appLifecycleState');
  }

  void _showToast1() {
    Fluttertoast.showToast(msg: "Showing a simple toast!");
  }

  void _showToast2() {
    Fluttertoast.showToast(
      msg: "Showing a different toast!",
      toastLength: Toast.LENGTH_LONG,
      gravity: ToastGravity.TOP,
      timeInSecForIosWeb: 1,
      backgroundColor: Colors.red,
      textColor: Colors.white,
      fontSize: 16.0,
    );
  }

  _openDialog1() async {
    // awaits for the future returned by showDialog
    //   to be resolved when the dialog is dismissed.
    await showDialog<String>(
      context: context,
      builder: (BuildContext context) => AlertDialog(
        title: Text("Title"),
        content: Text("Description"),
      )
    );
    // then, you may perform some action here.
    print("Action to be performed after the dialog is dismissed");
  }

  void _openDialog2() {
    showDialog(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: Text("Alert"),
          content: Text("Are you sure?"),
          actions: [
            TextButton(
                onPressed: () {
                  Navigator.pop(context, "Cancel");
                },
                child: Text("Cancel")
            ),
            TextButton(
                onPressed: () {
                  Navigator.popUntil(context, (route) => route.isFirst);
                  Navigator.pop(context);
                  // or you can use:
                  // exit(0);
                },
                child: Text("OK")
            ),
          ],
        )
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
                onPressed: _showToast1,
                child: Text("Open Toast 1")
            ),
            ElevatedButton(
                onPressed: _showToast2,
                child: Text("Open Toast 2")
            ),
            ElevatedButton(
                onPressed: _openDialog1,
                child: Text("Open Dialog 1")
            ),
            ElevatedButton(
                onPressed: _openDialog2,
                child: Text("Open Exit Dialog")
            ),
          ],
        ),
      ),
    );
  }
}
