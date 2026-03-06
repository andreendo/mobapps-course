import 'package:checkweather_fl/main_page.dart';
import 'package:checkweather_fl/main_view_model.dart';
import 'package:checkweather_fl/service/weather_repository.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  final repository = WeatherRepository();

  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => MainViewModel(repository: repository))
      ],
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Check Weather City',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blueGrey),
        useMaterial3: true,
      ),
      home: const MainPage(title: 'Check Weather City'),
    );
  }
}