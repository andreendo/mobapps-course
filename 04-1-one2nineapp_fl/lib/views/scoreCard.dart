import 'package:flutter/material.dart';

class ScoreCard extends StatelessWidget {
  final int position;
  final String name;
  final String gameTime;
  final String whenPlayed;

  const ScoreCard({
    required this.position,
    required this.name,
    required this.gameTime,
    required this.whenPlayed,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.all(8.0),
      child: ListTile(
        leading: CircleAvatar(
          child: Text('$position'),
        ),
        title: Text(name),
        subtitle: Text('Time: $gameTime\nPlayed: $whenPlayed'),
      ),
    );
  }
}

