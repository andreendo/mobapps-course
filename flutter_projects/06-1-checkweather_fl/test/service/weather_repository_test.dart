import 'package:checkweather_fl/service/weather_repository.dart';
import 'package:test/test.dart';

// run tests, >>dart test -r expanded

void main() {
  group('WeatherRepository', () {
    late WeatherRepository weatherRepository;

    setUp(() {
      weatherRepository = WeatherRepository();
    });

    test('getWeatherByCityName returns WeatherResponse on success', () async {
      final result = await weatherRepository.getWeatherByCityName('sao carlos');

      expect(result.temp, greaterThan(0));
    });

    test('getWeatherByCityName throws Exception on failure', () async {
      expect(() => weatherRepository.getWeatherByCityName('invalid_city'), throwsException);
    });
  });
}
