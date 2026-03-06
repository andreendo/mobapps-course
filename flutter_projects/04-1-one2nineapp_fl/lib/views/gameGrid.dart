import 'package:flutter/material.dart';

import '../game/number.dart';


class GameGrid extends StatelessWidget {

  final List<Number> numbers;
  final List<bool> marked;
  final void Function(int, int) onButtonClicked;

  const GameGrid({super.key,
    required this.numbers, required this.marked, required this.onButtonClicked});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Expanded(
          child: Row(
            children: [
              GameButton(
                index: 0,
                number: numbers[0],
                onButtonClicked: onButtonClicked,
                marked: marked[0],
              ),
              GameButton(
                index: 1,
                number: numbers[1],
                onButtonClicked: onButtonClicked,
                marked: marked[1],
              ),
              GameButton(
                index: 2,
                number: numbers[2],
                onButtonClicked: onButtonClicked,
                marked: marked[2],
              ),
            ],
          ),
        ),
        Expanded(
          child: Row(
            children: [
              GameButton(
                index: 3,
                number: numbers[3],
                onButtonClicked: onButtonClicked,
                marked: marked[3],
              ),
              GameButton(
                index: 4,
                number: numbers[4],
                onButtonClicked: onButtonClicked,
                marked: marked[4],
              ),
              GameButton(
                index: 5,
                number: numbers[5],
                onButtonClicked: onButtonClicked,
                marked: marked[5],
              ),
            ],
          ),
        ),
        Expanded(
          child: Row(
            children: [
              GameButton(
                index: 6,
                number: numbers[6],
                onButtonClicked: onButtonClicked,
                marked: marked[6],
              ),
              GameButton(
                index: 7,
                number: numbers[7],
                onButtonClicked: onButtonClicked,
                marked: marked[7],
              ),
              GameButton(
                index: 8,
                number: numbers[8],
                onButtonClicked: onButtonClicked,
                marked: marked[8],
              ),
            ],
          ),
        ),
      ],
    );
  }
}

class GameButton extends StatelessWidget {
  final int index;
  final Number number;
  final bool marked;
  final void Function(int, int) onButtonClicked;

  const GameButton({
    super.key,
    required this.index,
    required this.number,
    this.marked = false,
    required this.onButtonClicked});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Container(
        margin: const EdgeInsets.all(4.0),
        child: SizedBox(
          height: double.infinity,
          child: ElevatedButton(
            onPressed: () => onButtonClicked(index, number.value),
            child: Text(
              number.label,
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            style: ButtonStyle(
                backgroundColor: marked
                    ? MaterialStateProperty.all<Color>(Colors.lightBlue)
                    : null
            ),
          ),
        ),
      ),
    );
  }
}