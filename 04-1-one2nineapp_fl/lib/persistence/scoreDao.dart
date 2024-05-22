import 'package:floor/floor.dart';
import 'package:one2nineapp_fl/persistence/score.dart';

@dao
abstract class ScoreDao {

  @Insert(onConflict: OnConflictStrategy.abort)
  Future<void> insert(Score score);

  @Query('SELECT * FROM Score ORDER BY time ASC')
  Stream<List<Score>> getAllScores();

  @Query("DELETE FROM Score")
  Future<void> deleteAllScores();

  @Query("SELECT * FROM Score ORDER BY time ASC LIMIT 1")
  Future<Score?> getBestScore();

}