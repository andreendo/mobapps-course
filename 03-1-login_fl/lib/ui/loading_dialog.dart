
import 'package:flutter/material.dart';
import 'package:login_fl/l10n/app_localizations.dart';

class LoadingDialog extends StatelessWidget {
  const LoadingDialog({super.key});


  @override
  Widget build(BuildContext context) {
    return Dialog(
      child: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            CircularProgressIndicator(),
            SizedBox(width: 20),
            Text(AppLocalizations.of(context)!.loading),
          ],
        ),
      ),
    );
  }

}