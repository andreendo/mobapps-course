import 'package:checkweather_fl/service/weather_repository.dart';
import 'package:flutter/material.dart';

class MainViewModel extends ChangeNotifier {
  final WeatherRepository repository;

  MainViewModel({ required this.repository });

  // private mutable vars
  String _weatherLabel = "";
  String _weatherCity = "";
  bool _loading = false;

  // getters
  get weatherLabel => _weatherLabel;
  get loading => _loading;

  Future<void> getWeather(String city) async {
    String cityName = city.trim().isEmpty ? "São Carlos" : city;
    _weatherLabel = "";
    _loading = true;
    notifyListeners();

    try {
      var weatherResponse = await repository.getWeatherByCityName(cityName);
      double temp = weatherResponse.temp;
      _weatherCity = cityName;
      _weatherLabel = "Temperature: ${temp.toStringAsFixed(1)} °C";
    } catch (error) {
      _weatherLabel = "Error: $error";
    } finally {
      _loading = false;
      notifyListeners();
    }
  }

  void reset(String currentCityName) {
    if (_weatherLabel.isNotEmpty && currentCityName != _weatherCity) {
      _weatherLabel = "";
      notifyListeners();
    }
  }
}