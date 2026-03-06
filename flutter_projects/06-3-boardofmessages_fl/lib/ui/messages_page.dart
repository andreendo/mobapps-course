import 'package:boardofmessages_fl/ui/main_view_model.dart';
import 'package:boardofmessages_fl/ui/messages_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class MessagePage extends StatefulWidget {
  const MessagePage({super.key});

  @override
  State<MessagePage> createState() => _MessagePageState();

}

class _MessagePageState extends State<MessagePage> {

  late final MainViewModel viewModel;

  final _messageController = TextEditingController();
  final _fromController = TextEditingController();
  final _toController = TextEditingController();

  @override
  void initState() {
    print("_MessagePageState - initState()");
    super.initState();
    // use only to stop monitoring
    viewModel = Provider.of<MainViewModel>(context, listen: false);
  }

  @override
  void dispose() {
    print("_MessagePageState - dispose()");
    viewModel.stopMonitoringForBoard();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<MainViewModel>(context, listen: true);
    var board = viewModel.selectedBoard;

    if (board == null) {
      return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text('Board: error'),
        ),
        body: const Center(
          child: Text('Error'),
        ),
      );
    } else {
      return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text('Board: ${board.name}'),
        ),
        body: Center(
          child: Padding(
            padding: const EdgeInsets.all(10),
            child: Column(
              mainAxisSize: MainAxisSize.max,
              children: [
                MessageScreen(
                  messages: board.messages,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  mainAxisSize: MainAxisSize.max,
                  children: [
                    Expanded(
                      child: TextFormField(
                        controller: _fromController,
                        decoration: const InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: "From:"
                        ),
                      ),
                    ),
                    const SizedBox(width: 10,),
                    Expanded(
                      child: TextFormField(
                        controller: _toController,
                        decoration: const InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: "To:"
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 10,),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  mainAxisSize: MainAxisSize.max,
                  children: [
                    Expanded(
                        child: TextFormField(
                          controller: _messageController,
                          decoration: const InputDecoration(
                              border: OutlineInputBorder(),
                              labelText: "Message:"
                          ),
                        ),
                    ),
                    const SizedBox(width: 10,),
                    Container(
                      decoration: BoxDecoration(
                        color: Theme.of(context).primaryColor,
                        shape: BoxShape.circle,
                      ),
                      child: IconButton(
                        onPressed: () {
                          viewModel.postMessage(
                            board.id!,
                            _fromController.text,
                            _toController.text,
                            _messageController.text
                          );
                          _messageController.clear();
                          FocusScope.of(context).unfocus();
                        },
                        icon: const Icon(Icons.send),
                        color: Colors.white,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      );
    }
  }
  
}