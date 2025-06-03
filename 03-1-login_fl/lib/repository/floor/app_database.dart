// imports needed for the floor classes
import 'package:floor/floor.dart';
import 'dart:async';
import 'package:sqflite/sqflite.dart' as sqflite;

import 'package:login_fl/repository/floor/login_try_dao.dart';
import 'package:login_fl/repository/floor/login_try.dart';

part 'app_database.g.dart';

@Database(version: 1, entities: [LoginTry])
abstract class AppDatabase extends FloorDatabase {

  LoginTryDao get loginTryDao;

}