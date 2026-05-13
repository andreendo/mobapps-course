import 'package:test/test.dart';
import 'package:userauth/data/repositories/item_repository.dart';
import 'package:userauth/data/repositories/user_repository.dart';

void main() {
  final itemRepository = ItemRepository(true);
  final userRepository = UserRepository(true);

  test('get items successfully', () async {
    final token = await userRepository.login('user1', 'user1');
    final items = await itemRepository.getAll(token);
    expect(items.length, 1);
    print(items);
  });


  test('get items with invalid token', () async {
    expect(
            () => itemRepository.getAll('invalid token'),
        throwsException
    );
  });

}