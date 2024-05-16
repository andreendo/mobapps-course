import 'package:flutter/material.dart';
import 'package:imcapp_fl/person.dart';

class ConfirmPage extends StatelessWidget {
  const ConfirmPage(this.person, {super.key});

  final Person person;

  @override
  Widget build(BuildContext context) {

    void _calculate() {
      showDialog(
          context: context,
          builder: (BuildContext context) => AlertDialog(
            title: Text(
                "IMC",
                style: Theme.of(context).textTheme.headlineLarge,
            ),
            content: Text(
                "${person.name}'s IMC is ${person.imc()}.",
                style: Theme.of(context).textTheme.bodyLarge,
            ),
          )
      );
    }

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text('IMC App - confirmation'),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
            Table(
              border: TableBorder.all(),
              children: <TableRow>[
                TableRow(
                  children: [
                    TableCell(
                      child: TitleText(text: "Name:"),
                    ),
                    TableCell(
                        child: CommonText(text: person.name)
                    ),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: TitleText(text: "Age:"),
                    ),
                    TableCell(
                        child: CommonText(text: person.age.toString())
                    ),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: TitleText(text: "Height:"),
                    ),
                    TableCell(
                      child: CommonText(
                          text: "${person.height.toStringAsFixed(2)} m"
                      ),
                    ),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: TitleText(text: "Weight:"),
                    ),
                    TableCell(
                      child: CommonText(
                          text: "${person.weight.toStringAsFixed(1)} kg"
                      ),
                    ),
                  ],
                ),
              ],
            ),
            const SizedBox(height: 50),
            ElevatedButton(
                onPressed: _calculate,
                child: Text(
                    "Calculate",
                    style: Theme.of(context).textTheme.bodyLarge,
                )
            )
          ],
        ),
      ),
    );
  }
}

class TitleText extends StatelessWidget {
  const TitleText({super.key, required this.text});

  final String text;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(5),
      child: Text(
        style: const TextStyle(
          fontSize: 24,
          fontWeight: FontWeight.bold,
        ),
        text,
      ),
    );
  }
}

class CommonText extends StatelessWidget {
  const CommonText({super.key, required this.text});

  final String text;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(5),
      child: Text(
        style: TextStyle(
          fontSize: 24,
        ),
        text,
      ),
    );
  }
}