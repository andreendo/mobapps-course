import 'package:login_fl/repository/floor/login_try.dart';
import 'package:login_fl/repository/floor/login_try_dao.dart';

class LoginTryRepository {

  final LoginTryDao loginTryDao;

  LoginTryRepository(this.loginTryDao);

  Future<void> insert(LoginTry loginTry) => loginTryDao.insert(loginTry);

  Future<List<LoginTry>> getAll() => loginTryDao.getAll();

  Stream<List<LoginTry>> getAllInStream() => loginTryDao.getAllInStream();
}