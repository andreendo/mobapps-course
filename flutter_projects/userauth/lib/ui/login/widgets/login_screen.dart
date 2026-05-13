import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:userauth/ui/items/view_models/main_view_model.dart';
import 'package:userauth/ui/items/widgets/main_screen.dart';
import 'package:userauth/ui/login/view_models/login_view_model.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key, required this.title});

  final String title;

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {

  @override
  void initState() {
    super.initState();

    final viewModel = context.read<LoginViewModel>();
    viewModel.addListener(() {
      if (viewModel.isLoggedIn) {
        print('Navigate to main page');

        final mainViewModel = context.read<MainViewModel>();
        mainViewModel.loadInitialData();

        Navigator.of(context).push(
          MaterialPageRoute(builder: (context) => MainScreen())
        );
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final viewModel = context.watch<LoginViewModel>();

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: .center,
            children: [
              Text(
                'Perform login',
                style: Theme.of(context).textTheme.headlineLarge,
              ),
              Divider(
                height: 40,
                thickness: 2,
              ),
              SizedBox(height: 40,),
              TextFormField(
                controller: viewModel.usernameController,
                decoration: InputDecoration(
                  labelText: 'Username',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.person_2),
                ),
              ),
              SizedBox(height: 40,),
              TextFormField(
                controller: viewModel.passwordController,
                obscureText: true,
                decoration: InputDecoration(
                  labelText: 'Password',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.lock),
                ),
              ),
              SizedBox(height: 40,),
              FilledButton(
                onPressed: () => viewModel.clickLogin(),
                child: Text('Login')
              ),
              SizedBox(height: 40,),
              if (viewModel.errorMessage != null)
                Padding(
                  padding: EdgeInsetsGeometry.only(bottom: 10),
                  child: Text(
                    viewModel.errorMessage!,
                    style: TextStyle(
                        color: Theme.of(context).colorScheme.error,
                        fontSize: 14,
                        fontWeight: FontWeight.w500
                    ),
                  ),
                )
            ],
          ),
        ),
      ),
    );
  }
}
