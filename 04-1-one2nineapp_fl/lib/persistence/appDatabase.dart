// required package imports
import 'dart:async';
import 'package:floor/floor.dart';
import 'package:sqflite/sqflite.dart' as sqflite;

import 'package:one2nineapp_fl/persistence/score.dart';
import 'package:one2nineapp_fl/persistence/scoreDao.dart';

// Run the following command to generate the code needed:
// >> dart run build_runner build

// The file name below must be equal to the current file + '.g.dart'.
// So, here the name is 'appDatabase.g.dart'
part 'appDatabase.g.dart'; // the generated code will be in this file

@Database(version: 1, entities: [Score])
abstract class AppDatabase extends FloorDatabase {
  ScoreDao get scoreDao;
}