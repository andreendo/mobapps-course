
import 'package:shared_preferences/shared_preferences.dart';

class CounterRepository {
  int _counter = 0;

  Future<int> getInitialValue() async {
    // In Android Studio, open "device explorer"
    // Navigate to: data/data/com.example.share.../shared_prefs/<pref_file>.xml
    final prefs = await SharedPreferences.getInstance();
    _counter = prefs.getInt("counter") ?? 0;
    return _counter;
  }

  void increment() async {
    final prefs = await SharedPreferences.getInstance();
    _counter++;
    await prefs.setInt("counter", _counter);
  }

  void reset() async {
    final prefs = await SharedPreferences.getInstance();
    _counter = 0;
    await prefs.remove("counter");
  }
}