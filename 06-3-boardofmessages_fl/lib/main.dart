import 'package:boardofmessages_fl/ui/main_view_model.dart';
import 'package:flutter/material.dart';
import 'package:boardofmessages_fl/ui/app.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
      MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (_) => MainViewModel())
        ],
        child: const App(),
      )
  );
}