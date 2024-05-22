import 'package:flutter/material.dart';
import 'package:one2nineapp_fl/persistence/scoreRepository.dart';
import 'package:one2nineapp_fl/views/gameViewModel.dart';
import 'package:one2nineapp_fl/views/scoreViewModel.dart';
import 'package:provider/provider.dart';

import 'package:one2nineapp_fl/views/mainPage.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  final repository = ScoreRepository();
  await repository.initialize();

  runApp(
      MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (_) => GameViewModel(repository)),
          ChangeNotifierProvider(create: (_) => ScoreViewModel(repository)),
        ],
        child: const MyApp(),
      )
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'One 2 Nine',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MainPage(),
    );
  }
}