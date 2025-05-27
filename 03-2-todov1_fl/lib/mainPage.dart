import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:todov1_fl/addTaskPage.dart';
import 'package:todov1_fl/taskViewModel.dart';

import 'l10n/app_localizations.dart';

class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {

  late TaskViewModel viewModel;

  void _clickItem(int index) {
    print("clicked in $index");
    var task = viewModel.tasks[index];
    showDialog(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: Text(AppLocalizations.of(context)!.haveYouFinished),
          content: Text(AppLocalizations.of(context)!.task(task.description, task.name)),
          actions: [
            TextButton(
                onPressed: () {
                  viewModel.remove(index);
                  Navigator.pop(context);
                },
                child: Text(AppLocalizations.of(context)!.yes)
            ),
            TextButton(
                onPressed: () => Navigator.pop(context),
                child: Text(AppLocalizations.of(context)!.no)
            ),
          ],
        ),
    );
  }

  @override
  Widget build(BuildContext context) {
    viewModel = Provider.of<TaskViewModel>(context, listen: true);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(AppLocalizations.of(context)!.appName),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            ElevatedButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => const AddTaskPage()),
                  );
                },
                child: Text(AppLocalizations.of(context)!.addNewTask)
            ),
            const SizedBox(height: 16,),
            Expanded(
              child: ListView.builder(
                  itemCount: viewModel.tasks.length,
                  itemBuilder: (context, index) {
                    return Container(
                      decoration: BoxDecoration(
                        border: Border.all(color: Colors.grey),
                      ),
                      child: ListTile(
                        title: Text(viewModel.tasks[index].name),
                        onTap: () => _clickItem(index),
                      ),
                    );
                  },
              ),
            ),
          ],
        ),
      ),
    );
  }
}