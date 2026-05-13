import 'package:test/test.dart';
import 'package:userauth/data/repositories/user_repository.dart';

void main() {
  final userRepository = UserRepository(true);

  test('login successful', () async {
    final response = await userRepository.login('user1', 'user1');

    expect(response, isA<String>());
    expect(response, isNotEmpty);
    expect(response.split('.').length, equals(3));
  });


  test('login wrong user', () async {
    expect(
      () => userRepository.login('user5', '123'),
      throwsException
    );
  });

  test('login wrong password', () async {
    expect(
        () => userRepository.login('admin1', '123'),
        throwsException
    );
  });

}