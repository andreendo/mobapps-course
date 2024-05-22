import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'package:one2nineapp_fl/views/gameGrid.dart';
import 'package:one2nineapp_fl/views/gameViewModel.dart';


class GamePage extends StatefulWidget {
  const GamePage({super.key});

  @override
  State<StatefulWidget> createState() => _GamePageState();
}

class _GamePageState extends State<GamePage> {

  final _playerNameController = TextEditingController();

  // call when some dependency of State is changed
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    final viewModel = Provider.of<GameViewModel>(context);

    if (viewModel.showEndGameDialog) {
      // it ensures that the callback is run after the build method is completed.
      WidgetsBinding.instance.addPostFrameCallback((_) {
        showDialog<String>(
            context: context,
            builder: (context) => AlertDialog(
              title: const Text("End of the game"),
              content: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  TextFormField(
                    controller: _playerNameController,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Player name",
                    ),
                  ),
                  const SizedBox(height: 20,),
                  Text(viewModel.endGameDialogMessage),
                ],
              ),
              actions: [
                TextButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: const Text("OK"),
                ),
              ],
            ),
        ).then((value) {
          // onDismiss callback
          Navigator.pop(context);
          viewModel.endDialog(_playerNameController.text);
        });
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    GameViewModel viewModel = Provider.of<GameViewModel>(context);

    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: <Widget>[
            const Spacer(),
            const Text("00:03:22"),
            const SizedBox(width: 20,)
          ],
        ),
      ),
      body: GameGrid(
        numbers: viewModel.numbers,
        marked: viewModel.marked,
        onButtonClicked: (index, value) => viewModel.click(index, value),
      ),
    );
  }
}