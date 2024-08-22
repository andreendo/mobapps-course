class GeneralResponse {
  final String message;

  GeneralResponse({required this.message});

  // Factory constructor to create an instance from JSON
  factory GeneralResponse.fromJson(Map<String, dynamic> json) {
    return GeneralResponse(
      message: json['message'] as String,
    );
  }

  // Method to convert an instance to JSON
  Map<String, dynamic> toJson() {
    return {
      'message': message,
    };
  }
}
