import 'package:boardofmessages_fl/repository/board_repository.dart';
import 'package:test/test.dart';

// run tests, >>dart test -r expanded

void main() {
  final repository = BoardRepository(true);

  setUp(() async {
    await repository.reset();
  });

  test('deleteExistingBoard', () async {
    final response = await repository.deleteBoard(1);
    expect(response.message, equals('Board removed'));
  });

  test('DeleteInexistingBoard', () async {
    expect(
          () async => await repository.deleteBoard(1333),
          throwsA(isA<Exception>()),
    );
  });
}