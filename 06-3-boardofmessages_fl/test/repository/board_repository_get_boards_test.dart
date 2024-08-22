import 'dart:math';

import 'package:boardofmessages_fl/repository/board.dart';
import 'package:boardofmessages_fl/repository/board_repository.dart';
import 'package:test/test.dart';

// run tests, >>dart test -r expanded

void main() {
  final repository = BoardRepository(true);

  setUp(() async {
    await repository.reset();
  });

  test('getBoards', () async {
    final boards = await repository.getBoards();
    expect(boards.length, equals(2));
    expect(boards[0].name, equals("board_test1"));
    expect(boards[0].messages.length, equals(2));
    expect(boards[1].name, equals("board_test2"));
    expect(boards[1].messages.length, equals(1));
  });

  test('getBoard1', () async {
    // add board
    Board newBoard = Board(
      name: "new board 1",
      messages: [],
    );
    Board board = await repository.addBoard(newBoard);
    // This test may fail due to error in the server
    // With concurrent requests, the service is not reliable
    final rBoard = await repository.getBoard(board.id!);
    expect(rBoard.name, equals("new board 1"));
    expect(rBoard.messages.length, equals(0));
  });
}