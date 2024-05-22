
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:one2nineapp_fl/game/gameConfig.dart';
import 'package:one2nineapp_fl/game/number.dart';
import 'package:one2nineapp_fl/persistence/score.dart';
import 'package:one2nineapp_fl/persistence/scoreRepository.dart';

class GameViewModel extends ChangeNotifier {
  // constant vars
  final gameConfig = GameConfig();
  final stopwatch = Stopwatch();
  final ScoreRepository repository;

  // constructor
  GameViewModel(this.repository);

  // other mutable vars
  int current = 1;
  List<Number> _numbers = [
    for (var i = 1; i <= 9; i++)
      Number(value: i, label: i.toString())
  ];
  List<bool> _marked = [false, false, false, false, false, false, false, false, false];

  bool showEndGameDialog = false;
  String endGameDialogMessage = "";
  double elapsedTime = 0.0;

  // getters
  get numbers => _numbers;
  get marked => _marked;

  void startGame() {
    gameConfig.reset();
    stopwatch.reset();
    stopwatch.start();
    startRound();
  }

  void startRound() {
    _marked = [false, false, false, false, false, false, false, false, false];
    _numbers = gameConfig.getNextConfiguration();
    current = 1;
    notifyListeners();
  }

  void click(int index, int value) {
    print("Clicked in index: $index, value of number: $value");
    if (value == current) {
      current++;
      _marked[index] = true;
    }

    if (current == 10) {
      if (gameConfig.hasNext()) {
        startRound();
      } else {
        endGame();
      }
    }

    notifyListeners();
  }

  void endGame() async {
    elapsedTime = stopwatch.elapsed.inMilliseconds / 1000.0;
    print("End game $elapsedTime");

    var bestScore = await repository.getBestScore();
    var isBest = "";
    if (bestScore == null || elapsedTime < bestScore.time) {
      isBest = "NEW RECORD!!!\n";
    }

    endGameDialogMessage = "${isBest}Your time was ${elapsedTime.toStringAsFixed(2)} seconds.";
    showEndGameDialog = true;
    notifyListeners();
  }

  void endDialog(String playerName) {
    showEndGameDialog = false;
    // save score
    if (playerName.trim().isEmpty) {
      playerName = "Anonymous";
    }
    DateFormat dateFormat = DateFormat("dd-MM-yyyy HH:mm:ss");
    String whenPlayed = dateFormat.format(DateTime.now());
    var newScore = Score(playerName, elapsedTime, whenPlayed);
    repository.insert(newScore);
  }
}