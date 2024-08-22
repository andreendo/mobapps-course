class Message {
  final String from;
  final String to;
  final String text;
  final String timestamp;

  Message({
    required this.from,
    required this.to,
    required this.text,
    required this.timestamp,
  });

  @override
  String toString() {
    return 'Message(from: $from, to: $to, text: $text, timestamp: $timestamp)';
  }

  // Factory method to create an instance from a JSON map
  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      from: json['from'],
      to: json['to'],
      text: json['text'],
      timestamp: json['timestamp'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'from': from,
      'to': to,
      'text': text,
      'timestamp': timestamp,
    };
  }
}