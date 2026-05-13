
import 'package:dio/dio.dart';
import 'package:userauth/data/repositories/api_config.dart';
import 'package:userauth/data/repositories/dtos/login_request_form.dart';

class UserRepository {
  late final Dio _dio;

  UserRepository([bool test = false]) {
    _dio = ApiConfig.getDio(test);
  }

  Future<String> login(String username, String password) async {
    try {
      final loginRequestForm = LoginRequestForm(username: username, password: password);
      final response = await _dio.post(
        '/api/auth/login',
        data: loginRequestForm.toJson()
      );
      if (response.statusCode == 200) {
        return response.data;
      } else {
        throw Exception('failed login');
      }
    } catch(e) {
      print(e);
      throw e;
    }
  }
}