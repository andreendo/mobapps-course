class LoginRequestForm {
  final String username;
  final String password;

  LoginRequestForm({
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