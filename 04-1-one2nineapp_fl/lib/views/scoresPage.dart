import 'package:flutter/material.dart';
import 'package:one2nineapp_fl/persistence/score.dart';
import 'package:one2nineapp_fl/views/scoreCard.dart';
import 'package:one2nineapp_fl/views/scoreViewModel.dart';
import 'package:provider/provider.dart';

class ScoresPage extends StatefulWidget {
  const ScoresPage({super.key});

  @override
  State<StatefulWidget> createState() => _ScoresPageState();
}

class _ScoresPageState extends State<ScoresPage> {

  void _close() {
    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    ScoreViewModel viewModel = Provider.of<ScoreViewModel>(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text("See Scores Below"),
      ),
      body: Center(
        child: Column(
          children: [
            Expanded(
                child: StreamBuilder<List<Score>>(
                  stream: viewModel.getScoresStream(),
                  builder: (context, snapshot) {
                    if (snapshot.connectionState == ConnectionState.waiting) {
                      return Center(child: CircularProgressIndicator());
                    } else if (snapshot.hasError) {
                      return Center(child: Text('Error: ${snapshot.error}'));
                    } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
                      return Center(
                            child: Text(
                              'No scores available',
                              style: Theme.of(context).textTheme.bodyLarge,
                            )
                      );
                    }

                    final scores = snapshot.data!;
                    return ListView.builder(
                            itemCount: scores.length,
                            itemBuilder: (context, index) {
                              final item = scores[index];
                              return ScoreCard(
                                position: index + 1,
                                name: item.playerName,
                                gameTime: item.time.toStringAsFixed(2),
                                whenPlayed: item.whenPlayed,
                              );
                            },
                        );
                  },
                ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Padding(
                  padding: const EdgeInsets.all(20),
                  child: ElevatedButton(
                    onPressed: _close,
                    child: const Text("Close"),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(20),
                  child: ElevatedButton(
                    onPressed: () => viewModel.removeAllRecords(),
                    child: const Text("Clear All records"),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}