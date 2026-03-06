
import 'package:flutter/material.dart';
import 'package:one2nineapp_fl/persistence/score.dart';
import 'package:one2nineapp_fl/persistence/scoreRepository.dart';

class ScoreViewModel extends ChangeNotifier {

  final ScoreRepository repository;

  ScoreViewModel(this.repository);

  Stream<List<Score>> getScoresStream() => repository.getAllScores();

  removeAllRecords() => repository.deleteAllScores();
}