import 'dart:async';

import 'package:boardofmessages_fl/repository/board.dart';
import 'package:boardofmessages_fl/repository/board_repository.dart';
import 'package:boardofmessages_fl/repository/message.dart';
import 'package:flutter/material.dart';

class MainViewModel extends ChangeNotifier {
  final BoardRepository boardRepository = BoardRepository();

  int _numberOfBoards = 0;
  List<Board> _boards = [];
  Board? _selectedBoard;
  StreamSubscription<Board>? _boardStreamSubscription;

  get numberOfBoards => _numberOfBoards;
  get boards => _boards;
  get selectedBoard => _selectedBoard;

  Future<void> getBoards() async {
    print("MainViewModel - getBoards()");
    _numberOfBoards = 0;
    _boards = [];
    notifyListeners();
    // simulate a slower request
    await Future.delayed(const Duration(seconds: 2));
    final boards = await boardRepository.getBoards();
    print("MainViewModel - getBoards() $boards");
    _numberOfBoards = boards.length;
    _boards = boards;
    notifyListeners();

    startContinousCollection();
  }

  Future<void> startContinousCollection() async {
    var streamsOfBoards =
        boardRepository.getBoardsContinously(Duration(seconds: 4));
    await for (var newBoards in streamsOfBoards) {
      print(newBoards);
      _numberOfBoards = newBoards.length;
      _boards = newBoards;
      notifyListeners();
    }
  }

  void selectBoardForMessages(Board board) {
    print("Selected board: $board");
    _selectedBoard = board;
    startMonitoringForBoard();
  }

  void startMonitoringForBoard() {
    // send requests for board.id every 500ms
    var stream = boardRepository.getBoardContinously(
        _selectedBoard!.id!, Duration(milliseconds: 500));

    _boardStreamSubscription = stream.listen((board) {
      _selectedBoard = board;
    });
  }

  void stopMonitoringForBoard() {
    print('stopMonitoringForBoard');
    _boardStreamSubscription?.cancel();
    boardRepository.stopStreamingBoard();
  }

  Future<void> postMessage(
      int boardId, String from, String to, String textMessage) async {
    var message = Message(
        from: from.trim().isEmpty ? "anonymous" : from.trim(),
        to: to.trim().isEmpty ? "anonymous" : to.trim(),
        text: textMessage,
        timestamp: "");
    boardRepository.postMessage(boardId, message);
  }
}
