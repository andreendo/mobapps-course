import 'dart:math';

import 'number.dart';

class GameConfig {
  List<List<Number>> configurations = [];
  int current = 0;

  GameConfig() {
    configurations.add(getNumberNumber());
    // configurations.add(getNumberRoman());
    // configurations.add(getNumberEnglish());
    reset();
  }

  void reset() {
    current = 0;
  }

  List<Number> getNumberNumber() {
    return [
      Number(value: 1, label: '1'),
      Number(value: 2, label: '2'),
      Number(value: 3, label: '3'),
      Number(value: 4, label: '4'),
      Number(value: 5, label: '5'),
      Number(value: 6, label: '6'),
      Number(value: 7, label: '7'),
      Number(value: 8, label: '8'),
      Number(value: 9, label: '9')
    ];
  }

  List<Number> getNumberRoman() {
    return [
      Number(value: 1, label: 'I'),
      Number(value: 2, label: 'II'),
      Number(value: 3, label: 'III'),
      Number(value: 4, label: 'IV'),
      Number(value: 5, label: 'V'),
      Number(value: 6, label: 'VI'),
      Number(value: 7, label: 'VII'),
      Number(value: 8, label: 'VIII'),
      Number(value: 9, label: 'IX')
    ];
  }

  List<Number> getNumberEnglish() {
    return [
      Number(value: 1, label: 'one'),
      Number(value: 2, label: 'two'),
      Number(value: 3, label: 'three'),
      Number(value: 4, label: 'four'),
      Number(value: 5, label: 'five'),
      Number(value: 6, label: 'six'),
      Number(value: 7, label: 'seven'),
      Number(value: 8, label: 'eight'),
      Number(value: 9, label: 'nine')
    ];
  }

  List<Number> getNextConfiguration() {
    var ret = List<Number>.from(configurations[current]);
    ret.shuffle(Random());
    current++;
    return ret;
  }

  bool hasNext() {
    return current < configurations.length;
  }
}
