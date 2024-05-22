import 'package:floor/floor.dart';

@entity
class Score {
  @PrimaryKey(autoGenerate: true)
  int? id;

  final String playerName;

  final double time;

  final String whenPlayed;

  Score(this.playerName, this.time, this.whenPlayed);
}