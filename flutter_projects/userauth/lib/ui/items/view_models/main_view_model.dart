
import 'package:flutter/material.dart';
import 'package:userauth/data/repositories/dtos/item_list_form.dart';
import 'package:userauth/data/repositories/item_repository.dart';

import '../../../data/repositories/token_repository.dart';

class MainViewModel extends ChangeNotifier {

  final TokenRepository _tokenRepository;
  final ItemRepository _itemRepository;

  String username = "";
  List<ItemListForm> items = List.empty();

  MainViewModel(this._tokenRepository, this._itemRepository);

  Future<void> loadInitialData() async {
    username = "";
    items = List.empty();
    username = await _tokenRepository.getUsername() ?? 'unknown';
    notifyListeners();
  }

  Future<void> loadItems() async {
    print('loadItems()');
    final token = await _tokenRepository.getToken() ?? '';
    print(token);
    items = await _itemRepository.getAll(token);
    print('Items: ${items}');
    notifyListeners();
  }
}