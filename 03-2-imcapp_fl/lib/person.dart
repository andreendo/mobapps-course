import 'dart:math';
import 'dart:ffi';

class Person {
  final String name;
  final int age;
  final double height;
  final double weight;

  Person(this.name, this.age, this.height, this.weight);

  String imc() {
    double v = weight / pow(height, 2);
    return v.toStringAsFixed(2);
  }
}