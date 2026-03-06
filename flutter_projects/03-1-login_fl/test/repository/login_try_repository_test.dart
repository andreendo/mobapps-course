import 'package:flutter_test/flutter_test.dart';
import 'package:login_fl/repository/floor/app_database.dart';
import 'package:login_fl/repository/floor/login_try.dart';
import 'package:login_fl/repository/login_try_repository.dart';


void main() {
  late LoginTryRepository loginTryRepository;

  setUp(() async {
    // this test needs to have SQLite installed in your system
    // >> sudo apt install libsqlite3-dev
    final database = await $FloorAppDatabase
        .inMemoryDatabaseBuilder() // 💡 Use in-memory DB for tests
        .build();

    loginTryRepository = LoginTryRepository(database.loginTryDao);
  });

  test('Insert one try', () async {
    final loginTry = LoginTry(username: 'username',
        passwordLength: 5,
        wasSuccessful: false,
        whenTried: 'some day');
    await loginTryRepository.insert(loginTry);
    final list = await loginTryRepository.getAll();
    expect(list.length, equals(1));
    expect(list[0].username, equals(loginTry.username));
  });
}