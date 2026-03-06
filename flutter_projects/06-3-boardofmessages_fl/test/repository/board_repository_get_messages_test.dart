import 'package:boardofmessages_fl/repository/board_repository.dart';
import 'package:test/test.dart';

// run tests, >>dart test -r expanded

void main() {
  final repository = BoardRepository(true);

  setUp(() async {
    await repository.reset();
  });

  test('getMessages successfully', () async {
    final msgs = await repository.getMessages(1);
    expect(msgs.length, equals(2));
  });

  test('getMessages with error', () async {
    expect(
          () async => await repository.getMessages(1333),
          throwsA(isA<Exception>()), // Expecting DioError when the board does not exist
    );
  });
}