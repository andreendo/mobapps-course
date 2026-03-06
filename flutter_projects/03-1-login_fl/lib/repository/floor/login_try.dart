
import 'package:floor/floor.dart';

@entity
class LoginTry {
  @PrimaryKey(autoGenerate: true)
  int? id;

  final String username;

  final int passwordLength;

  final bool wasSuccessful;

  final String whenTried;

  LoginTry({this.id,
    required this.username,
    required this.passwordLength,
    required this.wasSuccessful,
    required this.whenTried});

  @override
  String toString() {
    return 'LoginTry(id: $id, username: $username, passwordLength: $passwordLength, wasSuccessful: $wasSuccessful, whenTried: $whenTried)';
  }
}