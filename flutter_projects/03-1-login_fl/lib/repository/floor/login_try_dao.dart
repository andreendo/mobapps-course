
import 'package:floor/floor.dart';
import 'package:login_fl/repository/floor/login_try.dart';

@dao
abstract class LoginTryDao {

  @Insert(onConflict: OnConflictStrategy.abort)
  Future<void> insert(LoginTry loginTry);

  @Query('SELECT * FROM LoginTry ORDER BY id DESC')
  Future<List<LoginTry>> getAll();

  @Query('SELECT * FROM LoginTry ORDER BY id DESC')
  Stream<List<LoginTry>> getAllInStream();

}