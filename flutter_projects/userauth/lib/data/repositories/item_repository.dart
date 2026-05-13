
import 'package:dio/dio.dart';
import 'package:userauth/data/repositories/api_config.dart';
import 'package:userauth/data/repositories/dtos/item_list_form.dart';

class ItemRepository {

  late final Dio _dio;

  ItemRepository([bool test = false]) {
    _dio = ApiConfig.getDio(test);
  }

  Future<List<ItemListForm>> getAll(String token) async {
    try {
      final response = await _dio.get(
        '/api/item',
        options: Options(
          headers: {
            'Authorization': 'Bearer $token',
            'Accept': 'application/json',
          },
        ),
      );

      if (response.statusCode == 200) {
        List<dynamic> data = response.data;
        return data.map((json) => ItemListForm.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load items');
      }
    } catch (e) {
      print(e);
      throw e;
    }
  }
}