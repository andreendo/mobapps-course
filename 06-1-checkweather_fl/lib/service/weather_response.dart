class WeatherResponse {
  final double temp;
  final double feelsLike;
  final double tempMin;
  final double tempMax;
  final int pressure;
  final int humidity;
  final int seaLevel;

  WeatherResponse({
    required this.temp,
    required this.feelsLike,
    required this.tempMin,
    required this.tempMax,
    required this.pressure,
    required this.humidity,
    required this.seaLevel,
  });

  factory WeatherResponse.fromJson(Map<String, dynamic> json) {
    final main = json['main'];
    return WeatherResponse(
      temp: main['temp'].toDouble(),
      feelsLike: main['feels_like'].toDouble(),
      tempMin: main['temp_min'].toDouble(),
      tempMax: main['temp_max'].toDouble(),
      pressure: main['pressure'].toInt(),
      humidity: main['humidity'].toInt(),
      seaLevel: main['sea_level'].toInt(),
    );
  }

  @override
  String toString() {
    return 'WeatherResponse(temp: $temp, feelsLike: $feelsLike, tempMin: $tempMin, tempMax: $tempMax, pressure: $pressure, humidity: $humidity, seaLevel: $seaLevel)';
  }
}