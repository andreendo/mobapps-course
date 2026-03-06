import 'message.dart';

class Board {
  int? id;
  final String name;
  final List<Message> messages;

  Board({
    this.id,
    required this.name,
    required this.messages,
  });

  @override
  String toString() {
    return 'Board(id: $id, name: $name, messages: $messages)';
  }

  // Factory method to create an instance from a JSON map
  factory Board.fromJson(Map<String, dynamic> json) {
    var messagesFromJson = json['messages'] as List;
    List<Message> messagesList = messagesFromJson.map((messageJson) => Message.fromJson(messageJson)).toList();

    return Board(
      id: json['id'],
      name: json['name'],
      messages: messagesList,
    );
  }

  // Convert a Board instance to JSON
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'messages': messages.map((message) => message.toJson()).toList(),
    };
  }
}
