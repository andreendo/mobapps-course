import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:userauth/data/repositories/item_repository.dart';
import 'package:userauth/data/repositories/token_repository.dart';
import 'package:userauth/data/repositories/user_repository.dart';
import 'package:userauth/ui/app.dart';
import 'package:userauth/ui/items/view_models/main_view_model.dart';
import 'package:userauth/ui/login/view_models/login_view_model.dart';

void main() {

  final UserRepository userRepository = UserRepository();
  final TokenRepository tokenRepository = TokenRepository();
  final ItemRepository itemRepository = ItemRepository();

  runApp(
    MultiProvider(providers: [
      ChangeNotifierProvider(create: (_) => LoginViewModel(userRepository, tokenRepository)),
      ChangeNotifierProvider(create: (_) => MainViewModel(tokenRepository, itemRepository))
    ],
    child: const App(),)
  );
}