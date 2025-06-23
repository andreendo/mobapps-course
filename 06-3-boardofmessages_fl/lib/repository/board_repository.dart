import 'package:boardofmessages_fl/repository/board.dart';
import 'package:boardofmessages_fl/repository/general_response.dart';
import 'package:boardofmessages_fl/repository/message.dart';
import 'package:dio/dio.dart';
import 'package:flutter/foundation.dart';

class BoardRepository {
  static const BASE_URL = "http://10.0.2.2:3000";
  static const TO_TEST_URL = "http://localhost:3000";

  // object used to make http requests
  late final Dio _dio;
  bool _isStreamingBoard = false;

  BoardRepository([bool test = false]) {
    if (test) {
      _dio = Dio(BaseOptions(baseUrl: TO_TEST_URL));
    } else {
      if (kIsWeb) {
        // the platform is web, use the same test url
        _dio = Dio(BaseOptions(baseUrl: TO_TEST_URL));
      } else {
        _dio = Dio(BaseOptions(baseUrl: BASE_URL));
      }
    }
  }

  Future<List<Board>> getBoards() async {
    final response = await _dio.get('/board');
    if (response.statusCode == 200) {
      var boardsFromJson = response.data as List;
      return boardsFromJson
          .map((boardJson) => Board.fromJson(boardJson))
          .toList();
    } else {
      throw Exception('Exception');
    }
  }

  Future<Board> addBoard(Board board) async {
    try {
      final response = await _dio.post(
        '/board',
        data: board.toJson(),
      );

      if (response.statusCode == 200 || response.statusCode == 201) {
        return Board.fromJson(response.data);
      } else {
        throw Exception('Failed to add the board');
      }
    } catch (e) {
      throw Exception('Error adding board: $e');
    }
  }

  Future<Board> getBoard(int iid) async {
    try {
      final response = await _dio.get('/board/$iid');
      if (response.statusCode == 200) {
        return Board.fromJson(response.data);
      } else {
        throw Exception('Failed to load board');
      }
    } catch (e) {
      throw Exception('Error getting board: $e');
    }
  }

  Future<GeneralResponse> deleteBoard(int iid) async {
    try {
      final response = await _dio.delete('/board/$iid');
      if (response.statusCode == 200) {
        return GeneralResponse.fromJson(response.data);
      } else {
        throw Exception('Failed to delete board');
      }
    } catch (e) {
      throw Exception('Error deleting board: $e');
    }
  }

  Future<GeneralResponse> postMessage(int iid, Message message) async {
    try {
      final response = await _dio.post(
        '/board/$iid/messages',
        data: message.toJson(),
      );
      if (response.statusCode == 200) {
        return GeneralResponse.fromJson(response.data);
      } else {
        throw Exception('Failed to post message');
      }
    } catch (e) {
      throw Exception('Error posting message: $e');
    }
  }

  Future<List<Message>> getMessages(int iid) async {
    try {
      final response = await _dio.get('/board/$iid/messages');
      if (response.statusCode == 200) {
        List<Message> messages = (response.data as List)
            .map((item) => Message.fromJson(item))
            .toList();
        return messages;
      } else {
        throw Exception('Failed to get messages');
      }
    } catch (e) {
      throw Exception('Error getting messages: $e');
    }
  }

  Future<GeneralResponse> reset() async {
    final response = await _dio.get('/reset');
    if (response.statusCode == 200) {
      return GeneralResponse.fromJson(response.data);
    } else {
      throw Exception('Exception');
    }
  }

  Stream<List<Board>> getBoardsContinously(Duration afterTime) async* {
    while (true) {
      try {
        print('getBoardsContinously - waiting for new request');
        await Future.delayed(afterTime);
        var boards = await getBoards();
        yield boards;
      } catch (e) {
        print('getBoardsContinously - Some error retrieving boards');
      } finally {}
    }
  }

  Stream<Board> getBoardContinously(int iid, Duration afterTime) async* {
    _isStreamingBoard = true;
    while (_isStreamingBoard) {
      try {
        var board = await getBoard(iid);
        yield board;
      } catch (e) {
        print('getBoard $iid Continously - Some error retrieving boards');
      } finally {
        print('getBoard $iid Continously - waiting for new request');
        await Future.delayed(afterTime);
      }
    }
  }

  // Stop active stream of a board
  void stopStreamingBoard() {
    _isStreamingBoard = false;
  }
}
