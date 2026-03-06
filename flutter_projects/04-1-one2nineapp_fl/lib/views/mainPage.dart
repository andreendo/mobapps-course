import 'package:flutter/material.dart';

import 'package:one2nineapp_fl/views/gamePage.dart';
import 'package:one2nineapp_fl/views/gameViewModel.dart';
import 'package:one2nineapp_fl/views/scoresPage.dart';
import 'package:provider/provider.dart';


class MainPage extends StatelessWidget {
  const MainPage({super.key});

  void _startNewGame(BuildContext context) {
    GameViewModel viewModel = Provider.of<GameViewModel>(context, listen: false);
    viewModel.startGame();

    Navigator.push(context, MaterialPageRoute(
        builder: (_) => const GamePage(),
    ));
  }

  void _viewScores(BuildContext context) {
    Navigator.push(context, MaterialPageRoute(
      builder: (_) => const ScoresPage(),
    ));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("One 2 Nine"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
                padding: const EdgeInsets.all(20),
                child: Text(
                  'In this game, you need to count from 1 until 9 by pressing the buttons in the correct order as fast as you can. The numbers show up with different labels!',
                  style: Theme.of(context).textTheme.bodyLarge,
                ),
            ),
            Padding(
              padding: const EdgeInsets.all(20),
              child: ElevatedButton(
                onPressed: () => _startNewGame(context),
                child: const Text("Start New Game"),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(20),
              child: ElevatedButton(
                onPressed: () => _viewScores(context),
                child: const Text("View Scores"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}