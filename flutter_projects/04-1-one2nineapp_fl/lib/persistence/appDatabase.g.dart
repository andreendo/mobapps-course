// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'appDatabase.dart';

// **************************************************************************
// FloorGenerator
// **************************************************************************

// ignore: avoid_classes_with_only_static_members
class $FloorAppDatabase {
  /// Creates a database builder for a persistent database.
  /// Once a database is built, you should keep a reference to it and re-use it.
  static _$AppDatabaseBuilder databaseBuilder(String name) =>
      _$AppDatabaseBuilder(name);

  /// Creates a database builder for an in memory database.
  /// Information stored in an in memory database disappears when the process is killed.
  /// Once a database is built, you should keep a reference to it and re-use it.
  static _$AppDatabaseBuilder inMemoryDatabaseBuilder() =>
      _$AppDatabaseBuilder(null);
}

class _$AppDatabaseBuilder {
  _$AppDatabaseBuilder(this.name);

  final String? name;

  final List<Migration> _migrations = [];

  Callback? _callback;

  /// Adds migrations to the builder.
  _$AppDatabaseBuilder addMigrations(List<Migration> migrations) {
    _migrations.addAll(migrations);
    return this;
  }

  /// Adds a database [Callback] to the builder.
  _$AppDatabaseBuilder addCallback(Callback callback) {
    _callback = callback;
    return this;
  }

  /// Creates the database and initializes it.
  Future<AppDatabase> build() async {
    final path = name != null
        ? await sqfliteDatabaseFactory.getDatabasePath(name!)
        : ':memory:';
    final database = _$AppDatabase();
    database.database = await database.open(
      path,
      _migrations,
      _callback,
    );
    return database;
  }
}

class _$AppDatabase extends AppDatabase {
  _$AppDatabase([StreamController<String>? listener]) {
    changeListener = listener ?? StreamController<String>.broadcast();
  }

  ScoreDao? _scoreDaoInstance;

  Future<sqflite.Database> open(
    String path,
    List<Migration> migrations, [
    Callback? callback,
  ]) async {
    final databaseOptions = sqflite.OpenDatabaseOptions(
      version: 1,
      onConfigure: (database) async {
        await database.execute('PRAGMA foreign_keys = ON');
        await callback?.onConfigure?.call(database);
      },
      onOpen: (database) async {
        await callback?.onOpen?.call(database);
      },
      onUpgrade: (database, startVersion, endVersion) async {
        await MigrationAdapter.runMigrations(
            database, startVersion, endVersion, migrations);

        await callback?.onUpgrade?.call(database, startVersion, endVersion);
      },
      onCreate: (database, version) async {
        await database.execute(
            'CREATE TABLE IF NOT EXISTS `Score` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `playerName` TEXT NOT NULL, `time` REAL NOT NULL, `whenPlayed` TEXT NOT NULL)');

        await callback?.onCreate?.call(database, version);
      },
    );
    return sqfliteDatabaseFactory.openDatabase(path, options: databaseOptions);
  }

  @override
  ScoreDao get scoreDao {
    return _scoreDaoInstance ??= _$ScoreDao(database, changeListener);
  }
}

class _$ScoreDao extends ScoreDao {
  _$ScoreDao(
    this.database,
    this.changeListener,
  )   : _queryAdapter = QueryAdapter(database, changeListener),
        _scoreInsertionAdapter = InsertionAdapter(
            database,
            'Score',
            (Score item) => <String, Object?>{
                  'id': item.id,
                  'playerName': item.playerName,
                  'time': item.time,
                  'whenPlayed': item.whenPlayed
                },
            changeListener);

  final sqflite.DatabaseExecutor database;

  final StreamController<String> changeListener;

  final QueryAdapter _queryAdapter;

  final InsertionAdapter<Score> _scoreInsertionAdapter;

  @override
  Stream<List<Score>> getAllScores() {
    return _queryAdapter.queryListStream(
        'SELECT * FROM Score ORDER BY time ASC',
        mapper: (Map<String, Object?> row) => Score(row['playerName'] as String,
            row['time'] as double, row['whenPlayed'] as String),
        queryableName: 'Score',
        isView: false);
  }

  @override
  Future<void> deleteAllScores() async {
    await _queryAdapter.queryNoReturn('DELETE FROM Score');
  }

  @override
  Future<Score?> getBestScore() async {
    return _queryAdapter.query('SELECT * FROM Score ORDER BY time ASC LIMIT 1',
        mapper: (Map<String, Object?> row) => Score(row['playerName'] as String,
            row['time'] as double, row['whenPlayed'] as String));
  }

  @override
  Future<void> insert(Score score) async {
    await _scoreInsertionAdapter.insert(score, OnConflictStrategy.abort);
  }
}
