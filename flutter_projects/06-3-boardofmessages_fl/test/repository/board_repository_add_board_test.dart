import 'package:boardofmessages_fl/repository/board.dart';
import 'package:boardofmessages_fl/repository/board_repository.dart';
import 'package:test/test.dart';

// run tests, >>dart test -r expanded

void main() {
  final repository = BoardRepository(true);

  setUp(() async {
    await repository.reset();
  });

  test('addBoard successfully', () async {
    Board newBoard = Board(
      name: "new board",
      messages: [],
    );
    Board board = await repository.addBoard(newBoard);
    expect(board.name, equals("new board"));
    expect(board.id, equals(3));
    expect(board.messages.isEmpty, equals(true));
  });
}