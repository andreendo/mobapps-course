class UserData {
  final String username;
  final String password;

  UserData({
    required this.username,
    required this.password
  });

  Map<String, dynamic> toJson() {
    return {
      'username' : username,
      'password' : password
    };
  }
}