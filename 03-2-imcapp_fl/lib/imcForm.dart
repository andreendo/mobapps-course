import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:imcapp_fl/confirmPage.dart';
import 'package:imcapp_fl/person.dart';
import 'package:imcapp_fl/validators.dart';

class ImcForm extends StatefulWidget {
  const ImcForm({super.key});

  @override
  State<ImcForm> createState() => _ImcFormState();
}

class _ImcFormState extends State<ImcForm> {

  // global key for form validation
  final _formKey = GlobalKey<FormState>();

  final _nameController = TextEditingController();
  final _ageController = TextEditingController();
  final _heightController = TextEditingController();
  final _weightController = TextEditingController();

  void _clearData() {
    _nameController.clear();
    _ageController.text = "";
    _heightController.text = "";
    _weightController.text = "";
  }

  void _submitData() {
    if (_formKey.currentState!.validate()) {
      print("Submit data");
      int age = int.tryParse(_ageController.text) ?? 0;
      double h = double.tryParse(_heightController.text) ?? 1.50;
      double w = double.tryParse(_weightController.text) ?? 50.0;
      var person = Person(_nameController.text, age, h, w);
      Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => ConfirmPage(person)),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Form(
        key: _formKey,
        child: Center(
          child: Padding(
            padding: EdgeInsets.all(15),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                TextFormField(
                  controller: _nameController,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: "Name",
                  ),
                ),
                const SizedBox(height: 16.0),
                TextFormField(
                  controller: _ageController,
                  keyboardType: TextInputType.number,
                  validator: validateInteger,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: "Age",
                  ),
                ),
                const SizedBox(height: 16.0),
                TextFormField(
                  controller: _heightController,
                  keyboardType: TextInputType.number,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: "Height",
                  ),
                ),
                const SizedBox(height: 16.0),
                TextFormField(
                  controller: _weightController,
                  keyboardType: TextInputType.number,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: "Weight",
                  ),
                ),
                const SizedBox(height: 16.0),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                        onPressed: _clearData,
                        child: const Text("Clear Data")
                    ),
                    const SizedBox(width: 16.0),
                    ElevatedButton(
                        onPressed: _submitData,
                        child: const Text("Submit")
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
    );
  }
}