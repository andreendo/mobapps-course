import 'package:test/test.dart';
import 'package:login_fl/repository/user_repository.dart';

// run tests, >> dart test -r expanded

void main() {
  final userRepository = UserRepository(true);

  test('login successful', () async {
    final response = await userRepository.login('user1', '123');
    expect(response, equals('success'));
  });

  test('login wrong user', () async {
    final response = await userRepository.login('user5', '123');
    expect(response, equals('wrong_username'));
  });

  test('login wrong password', () async {
    final response = await userRepository.login('user1', '456');
    expect(response, equals('wrong_password'));
  });
}