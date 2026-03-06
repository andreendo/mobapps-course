
String? validateInteger(String? value) {
  if (value == null || value.isEmpty) {
    return "Enter some text";
  }

  if (int.tryParse(value) == null) {
    return "Enter integer value";
  }

  return null;
}