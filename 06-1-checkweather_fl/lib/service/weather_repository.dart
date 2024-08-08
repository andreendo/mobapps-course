import 'package:dio/dio.dart';
import 'weather_response.dart';


class WeatherRepository {
  final String _appid = "1b3d0e73c2837e05efe700a16d3756e2";

  final _dio = Dio(
      BaseOptions(baseUrl: "https://api.openweathermap.org/data/2.5")
  );

  Future<WeatherResponse> getWeatherByCityName(String cityName, [ String units = "metric"]) async {
    final response = await _dio.get(
      '/weather',
      queryParameters: {
        'appid': _appid,
        'q': cityName,
        'units': units,
      },
    );

    if (response.statusCode == 200) {
      return WeatherResponse.fromJson(response.data);
    } else {
      throw Exception('Failed to load weather data');
    }
  }
}