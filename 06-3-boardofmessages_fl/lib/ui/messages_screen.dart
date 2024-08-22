import 'package:flutter/material.dart';

import '../repository/message.dart';

class MessageScreen extends StatelessWidget {

  final List<Message> messages;

  MessageScreen({ required this.messages });

  @override
  Widget build(BuildContext context) {
    return Expanded(
        child: ListView.builder(
            itemCount: messages.length,
            itemBuilder: (context, index) {
              final message = messages[index];
              bool side = index % 2 == 0;

              return Align(
                alignment: (side) ? Alignment.centerRight : Alignment.centerLeft,
                child: Container(
                  margin: const EdgeInsets.symmetric(vertical: 4.0),
                  padding: const EdgeInsets.all(10.0),
                  constraints: BoxConstraints(maxWidth: MediaQuery.of(context).size.width * 0.75),
                  decoration: BoxDecoration(
                    color: side ? Colors.blueAccent : Colors.grey[300],
                    borderRadius: BorderRadius.circular(12.0),
                  ),
                  child: Column(
                    crossAxisAlignment: side ? CrossAxisAlignment.end : CrossAxisAlignment.start,
                    children: [
                      Text(
                      message.text,
                      style: TextStyle(color: side ? Colors.white : Colors.black),
                      ),
                      const SizedBox(height: 5),
                      Text(
                        message.timestamp.substring(0, 25),
                        style: TextStyle(
                          color: side ? Colors.white70 : Colors.black54,
                          fontSize: 10.0,
                        ),
                      ),
                    ],
                  ),
                ),
              );
            }
        ),
    );
  }
  
}