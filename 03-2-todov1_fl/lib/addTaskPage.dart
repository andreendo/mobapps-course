import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:todov1_fl/taskViewModel.dart';

import 'package:flutter_gen/gen_l10n/app_localizations.dart';
import 'package:todov1_fl/validators.dart';

class AddTaskPage extends StatefulWidget {
  const AddTaskPage({super.key});

  @override
  State<AddTaskPage> createState() => _AddTaskPageState();
}

class _AddTaskPageState extends State<AddTaskPage> {

  final _formKey = GlobalKey<FormState>();

  // late -> I promise it will be init before its 1st use.
  late TaskViewModel viewModel;

  final _nameController = TextEditingController();
  final _descController = TextEditingController();

  @override
  void initState() {
    super.initState();
    // listen: false -> avoid to rebuild the screen when there is state changes
    viewModel = Provider.of<TaskViewModel>(context, listen: false);
  }

  void _add() {
    if (_formKey.currentState!.validate()) {
      viewModel.addTask(_nameController.text, _descController.text);
      Navigator.pop(context);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Form(
        key: _formKey,
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            children: [
              TextFormField(
                controller: _nameController,
                validator: (value) => validate(context, value),
                decoration: InputDecoration(
                  border: const OutlineInputBorder(),
                  labelText: AppLocalizations.of(context)!.taskName
                ),
              ),
              const SizedBox(height: 16,),
              TextFormField(
                controller: _descController,
                validator: (value) => validate(context, value),
                decoration: InputDecoration(
                  border: const OutlineInputBorder(),
                  labelText: AppLocalizations.of(context)!.taskDesc
                ),
              ),
              const SizedBox(height: 16,),
              ElevatedButton(
                  onPressed: _add,
                  child: Text(
                      AppLocalizations.of(context)!.add,
                      style: Theme.of(context).textTheme.bodyLarge,
                  )
              )
            ],
          ),
        ),
      ),
    );
  }
}