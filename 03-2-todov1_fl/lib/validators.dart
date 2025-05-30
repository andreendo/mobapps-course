import 'package:flutter/material.dart';
import 'l10n/app_localizations.dart';

String? validate(BuildContext context, String? value) {
  if (value == null || value.isEmpty) {
    return AppLocalizations.of(context)!.errorEmptyText;
  }

  return null;
}