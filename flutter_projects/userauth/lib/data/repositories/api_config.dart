
import 'package:dio/dio.dart';

class ApiConfig {

  static const _BASE_URL = "http://10.0.2.2:8080";
  static const _TO_TEST_URL = "http://localhost:8080";

  static Dio getDio([bool test = false]) {
    if (test) {
      return Dio(BaseOptions(baseUrl: _TO_TEST_URL));
    } else {
      return Dio(BaseOptions(baseUrl: _BASE_URL));
    }
  }
}