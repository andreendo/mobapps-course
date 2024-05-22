
import 'package:one2nineapp_fl/persistence/appDatabase.dart';
import 'package:one2nineapp_fl/persistence/score.dart';
import 'package:one2nineapp_fl/persistence/scoreDao.dart';

class ScoreRepository {

  late AppDatabase database;
  late ScoreDao scoreDao;

  Future<void> initialize() async {
    database = await $FloorAppDatabase.databaseBuilder('app_database.db').build();
    scoreDao = database.scoreDao;
  }

  Future<void> insert(Score score) => scoreDao.insert(score);

  Stream<List<Score>> getAllScores() => scoreDao.getAllScores();

  Future<void> deleteAllScores() => scoreDao.deleteAllScores();

  Future<Score?> getBestScore() => scoreDao.getBestScore();
}