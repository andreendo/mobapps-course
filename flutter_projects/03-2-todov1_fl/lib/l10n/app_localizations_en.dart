// ignore: unused_import
import 'package:intl/intl.dart' as intl;
import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for English (`en`).
class AppLocalizationsEn extends AppLocalizations {
  AppLocalizationsEn([String locale = 'en']) : super(locale);

  @override
  String get appName => 'ToDo V1';

  @override
  String get addNewTask => 'Add New Task';

  @override
  String get add => 'Add';

  @override
  String get yes => 'Yes';

  @override
  String get no => 'No';

  @override
  String get haveYouFinished => 'Have you finished?';

  @override
  String task(Object description, Object name) {
    return 'Task: $name\nDescription: $description';
  }

  @override
  String get taskName => 'Task Name';

  @override
  String get taskDesc => 'Task Description';

  @override
  String get errorEmptyText => 'Enter some text.';
}
