class ResponseData {
  final String message;

  ResponseData({
    required this.message
  });

  factory ResponseData.fromJson(Map<String, dynamic> json) {
    return ResponseData(message: json['message']);
  }
}