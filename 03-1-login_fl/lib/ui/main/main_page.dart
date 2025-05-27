import 'package:flutter/material.dart';
import 'package:login_fl/l10n/app_localizations.dart';

class MainPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(AppLocalizations.of(context)!.main_page),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(AppLocalizations.of(context)!.some_text_here),
            FilledButton(
                onPressed: () {}, 
                child: Text(AppLocalizations.of(context)!.logout))
          ],
        ),
      ),
    );
  }
}