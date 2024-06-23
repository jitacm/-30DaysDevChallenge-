import 'package:flutter/material.dart';

void main() {
  runApp(CalculatorApp());
}

class CalculatorApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Simple Calculator',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: CalculatorHomePage(),
    );
  }
}

class CalculatorHomePage extends StatefulWidget {
  @override
  _CalculatorHomePageState createState() => _CalculatorHomePageState();
}

class _CalculatorHomePageState extends State<CalculatorHomePage> {
  String _output = "0";
  String _input = "";
  double _num1 = 0.0;
  double _num2 = 0.0;
  String _operator = "";

  void _buttonPressed(String buttonText) {
    if (buttonText == "C") {
      _output = "0";
      _input = "";
      _num1 = 0.0;
      _num2 = 0.0;
      _operator = "";
    } else if (buttonText == "+" || buttonText == "-" || buttonText == "*" || buttonText == "/") {
      _num1 = double.parse(_output);
      _operator = buttonText;
      _input = "";
    } else if (buttonText == ".") {
      if (!_input.contains(".")) {
        _input += buttonText;
      }
    } else if (buttonText == "=") {
      _num2 = double.parse(_output);

      if (_operator == "+") {
        _output = (_num1 + _num2).toString();
      } else if (_operator == "-") {
        _output = (_num1 - _num2).toString();
      } else if (_operator == "*") {
        _output = (_num1 * _num2).toString();
      } else if (_operator == "/") {
        _output = (_num1 / _num2).toString();
      }

      _num1 = 0.0;
      _num2 = 0.0;
      _operator = "";
      _input = _output;
    } else {
      _input += buttonText;
    }

    setState(() {
      _output = double.parse(_input).toString();
    });
  }

  Widget _buildButton(String buttonText) {
    return Expanded(
      child: OutlinedButton(
        style: OutlinedButton.styleFrom(
          padding: EdgeInsets.all(24.0),
        ),
        child: Text(
          buttonText,
          style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
        ),
        onPressed: () => _buttonPressed(buttonText),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Simple Calculator'),
      ),
      body: Column(
        children: <Widget>[
          Container(
            alignment: Alignment.centerRight,
            padding: EdgeInsets.symmetric(vertical: 24.0, horizontal: 12.0),
            child: Text(
              _output,
              style: TextStyle(fontSize: 48.0, fontWeight: FontWeight.bold),
            ),
          ),
          Expanded(
            child: Divider(),
          ),
          Column(children: [
            Row(children: [
              _buildButton("7"),
              _buildButton("8"),
              _buildButton("9"),
              _buildButton("/"),
            ]),
            Row(children: [
              _buildButton("4"),
              _buildButton("5"),
              _buildButton("6"),
              _buildButton("*"),
            ]),
            Row(children: [
              _buildButton("1"),
              _buildButton("2"),
              _buildButton("3"),
              _buildButton("-"),
            ]),
            Row(children: [
              _buildButton("."),
              _buildButton("0"),
              _buildButton("00"),
              _buildButton("+"),
            ]),
            Row(children: [
              _buildButton("C"),
              _buildButton("="),
            ]),
          ]),
        ],
      ),
    );
  }
}
