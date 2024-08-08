import 'package:checkweather_fl/main_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class MainPage extends StatefulWidget {
  const MainPage({super.key, required this.title});

  final String title;

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {

  final _cityNameController = TextEditingController();

  @override
  void initState() {
    super.initState();

    // listen to changes in the text controller
    _cityNameController.addListener(() {
      final viewModel = Provider.of<MainViewModel>(context, listen: false);
      viewModel.reset(_cityNameController.text);
    });
  }

  @override
  void dispose() {
    _cityNameController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    MainViewModel viewModel = Provider.of<MainViewModel>(context);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: [
              TextFormField(
                controller: _cityNameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: "City Name",
                ),
              ),
              ElevatedButton(
                  onPressed: () => viewModel.getWeather(_cityNameController.text),
                  child: const Text("Retrieve Weather")
              ),
              const SizedBox(height: 10,),
              viewModel.loading
              ? LinearProgressIndicator(
                color: Theme.of(context).colorScheme.secondary,
                backgroundColor: Theme.of(context).colorScheme.surfaceVariant,
              )
              : Text(
                viewModel.weatherLabel,
                style: Theme.of(context).textTheme.headlineMedium,
              )
            ],
          ),
        ),
      ),
    );
  }
}