import 'package:dio/dio.dart';
import 'package:login_fl/repository/response_data.dart';
import 'package:login_fl/repository/user_data.dart';

class UserRepository {

  static const _BASE_URL = "http://10.0.2.2:3001";
  static const _TO_TEST_URL = "http://localhost:3001";

  late final Dio _dio;

  UserRepository([bool test = false]) {
    if (test) {
      _dio = Dio(BaseOptions(baseUrl: _TO_TEST_URL));
    } else {
      _dio = Dio(BaseOptions(baseUrl: _BASE_URL));
    }
  }

  Future<String> login(String username, String password) async {
    try {
      final userData = UserData(username: username, password: password);
      final response = await _dio.post(
        '/login',
        data: userData.toJson()
      );
      if (response.statusCode == 200 || response.statusCode == 201) {
        final responseData = ResponseData.fromJson(response.data);
        if (responseData.message == 'success') {
          return 'success';
        } else if (responseData.message == 'unexisting username') {
          return 'wrong_username';
        } else if (responseData.message == 'wrong password') {
          return 'wrong_password';
        } else {
          return responseData.message;
        }
      } else {
        throw Exception('failed status code');
      }
    } catch(e) {
      print(e);
      return 'failed_connection';
    }
  }

  /*
  Future<String> login(String username, String password) async {

    await Future.delayed(const Duration(seconds: 2));

    if (!['user1', 'user2'].contains(username)) {
      return 'wrong_username';
    } else if (username == 'user1' && password != '123') {
      return 'wrong_password';
    } else if (username == 'user1' && password == '123') {
      return 'success';
    } else {
      return 'failed_connection';
    }
  }
  */
}