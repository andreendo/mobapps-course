
import 'package:flutter/material.dart';

class CustomButton extends StatelessWidget {
  //constructor
  const CustomButton({
        super.key,
        required this.label,
        this.hasCheck = false,
        this.marked = false,
        required this.onPressed,
      });

  final String label;
  final bool hasCheck;
  final bool marked;
  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: onPressed,
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: [
          if (hasCheck)
            Row(
              mainAxisSize: MainAxisSize.min,
              children: [
                if (!marked) Icon(Icons.check) else Icon(Icons.check_box_rounded),
                const SizedBox(width: 10),
              ],
            ),
          Text(label)
        ],
      ),
    );
  }
  
}