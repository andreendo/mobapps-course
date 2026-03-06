import 'package:flutter/material.dart';
import 'package:login_fl/l10n/app_localizations.dart';
import 'package:login_fl/viewmodels/login_tries_view_model.dart';
import 'package:provider/provider.dart';

class LoginTriesPage extends StatelessWidget {
  const LoginTriesPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(AppLocalizations.of(context)!.login_tries_page),
      ),
      body: Consumer<LoginTriesViewModel>(
        builder: (context, viewmodel, child) {
          return Center(
            child: Column(
                children: [
                  SizedBox(height: 10,),
                  Text(
                      style: Theme.of(context).textTheme.headlineSmall,
                      '${AppLocalizations.of(context)!.login_tries_so_far} ${viewmodel.loginTries.length}'
                  ),
                  SizedBox(height: 10,),
                  Expanded(
                    child: ListView.builder(
                      itemCount: viewmodel.loginTries.length,
                      itemBuilder: (context, index) {
                        return TryCard(text: viewmodel.loginTries[index].toString());
                      }
                    )
                  ),
                ]
            ),
          );
        }
      )
    );
  }
}

class TryCard extends StatelessWidget {
  final String text;

  const TryCard({super.key, required this.text});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        border: Border.all(color: Colors.black38),
      ),
      child: ListTile(
        title: Text(text),
      ),
    );
  }
}