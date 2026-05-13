
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:userauth/ui/items/view_models/main_view_model.dart';

class MainScreen extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    final viewModel = context.watch<MainViewModel>();

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text('Items'),
        actions: [
          IconButton(
            icon: const Icon(Icons.logout_sharp),
            tooltip: 'logout button',
            onPressed: () {},
          )
        ],
      ),
      body: Center(
        child: Padding(
          padding: EdgeInsetsGeometry.all(16.0),
          child: Column(
            mainAxisAlignment: .start,
            children: [
              Text(
                'Main Page',
                style: Theme.of(context).textTheme.headlineLarge,
              ),
              Divider(
                height: 40,
                thickness: 2,
              ),
              Text(
                'Logged user: ${viewModel.username}',
                style: Theme.of(context).textTheme.bodyLarge,
              ),
              FilledButton(
                onPressed: () => viewModel.loadItems(),
                child: const Text('Load Items')
              ),
              Divider(
                height: 40,
                thickness: 2,
              ),
              _buildList(viewModel)
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildList(MainViewModel vm) {
    if (vm.items.isEmpty) {
      return const Center(child: Text('No items loaded.'));
    }

    return Center(child: Text('${vm.items.length} items loaded.'));
  }
}