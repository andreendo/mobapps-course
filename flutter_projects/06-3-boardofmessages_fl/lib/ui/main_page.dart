import 'package:boardofmessages_fl/ui/main_view_model.dart';
import 'package:boardofmessages_fl/ui/messages_page.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class MainPage extends StatefulWidget {
  const MainPage({super.key, required this.title});

  final String title;

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<MainViewModel>(context);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            const SizedBox(height: 10,),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                    onPressed: () => viewModel.getBoards(),
                    child: const Text('Get Boards')
                ),
                ElevatedButton(
                    onPressed: () {},
                    child: const Text('Add New Board')
                )
              ],
            ),
            const SizedBox(height: 20,),
            Text(
              "Number of boards available: ${viewModel.numberOfBoards}",
              style: Theme.of(context).textTheme.bodyLarge,
            ),
            const SizedBox(height: 10,),
            Expanded(
                child: ListView.builder(
                    itemCount: viewModel.numberOfBoards,
                    itemBuilder: (context, index) {
                      final board = viewModel.boards[index];

                      return Card(
                        margin: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
                        elevation: 4,
                        child: ListTile(
                          contentPadding: const EdgeInsets.all(16),
                          title: Text(
                            board.name,
                            style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                          ),
                          subtitle: Text('Messages: ${board.messages.length}'),
                          leading: CircleAvatar(
                            backgroundColor: Colors.blueAccent,
                            foregroundColor: Colors.white,
                            child: Text(board.id?.toString() ?? '?'),
                          ),
                          isThreeLine: true,
                          trailing: IconButton(
                            icon: const Icon(Icons.message),
                            onPressed: () {
                              viewModel.selectBoardForMessages(board);
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => MessagePage())
                              );
                            },
                            color: Colors.blue,
                          ),
                        ),
                      );
                    }
                )
            ),
          ],
        ),
      ),
    );
  }
}
