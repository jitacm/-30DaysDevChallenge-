import 'package:flutter/material.dart';



class UnitConverterApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Unit Converter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: UnitConverterHomePage(),
    );
  }
}

class UnitConverterHomePage extends StatefulWidget {
  @override
  _UnitConverterHomePageState createState() => _UnitConverterHomePageState();
}

class _UnitConverterHomePageState extends State<UnitConverterHomePage> {
  String _selectedCategory = 'Length';
  String _fromUnit = 'Meter';
  String _toUnit = 'Kilometer';
  double? _inputValue;
  double? _convertedValue;

  final Map<String, List<String>> _units = {
    'Length': ['Meter', 'Kilometer', 'Centimeter', 'Millimeter'],
    'Weight': ['Kilogram', 'Gram', 'Pound', 'Ounce'],
  };

  final Map<String, Function> _conversionFunctions = {
    'Length': convertLength,
    'Weight': convertWeight,
  };

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Unit Converter'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            DropdownButton<String>(
              value: _selectedCategory,
              onChanged: (String? newValue) {
                setState(() {
                  _selectedCategory = newValue!;
                  _fromUnit = _units[_selectedCategory]!.first;
                  _toUnit = _units[_selectedCategory]!.first;
                });
              },
              items: _units.keys.map<DropdownMenuItem<String>>((String category) {
                return DropdownMenuItem<String>(
                  value: category,
                  child: Text(category),
                );
              }).toList(),
            ),
            Row(
              children: [
                Expanded(
                  child: DropdownButton<String>(
                    value: _fromUnit,
                    onChanged: (String? newValue) {
                      setState(() {
                        _fromUnit = newValue!;
                      });
                    },
                    items: _units[_selectedCategory]!
                        .map<DropdownMenuItem<String>>((String unit) {
                      return DropdownMenuItem<String>(
                        value: unit,
                        child: Text(unit),
                      );
                    }).toList(),
                  ),
                ),
                Expanded(
                  child: DropdownButton<String>(
                    value: _toUnit,
                    onChanged: (String? newValue) {
                      setState(() {
                        _toUnit = newValue!;
                      });
                    },
                    items: _units[_selectedCategory]!
                        .map<DropdownMenuItem<String>>((String unit) {
                      return DropdownMenuItem<String>(
                        value: unit,
                        child: Text(unit),
                      );
                    }).toList(),
                  ),
                ),
              ],
            ),
            TextField(
              decoration: InputDecoration(labelText: 'Input Value'),
              keyboardType: TextInputType.number,
              onChanged: (String value) {
                setState(() {
                  _inputValue = double.tryParse(value);
                });
              },
            ),
            ElevatedButton(
              onPressed: () {
                setState(() {
                  _convertedValue = _conversionFunctions[_selectedCategory]!(
                      _inputValue, _fromUnit, _toUnit);
                });
              },
              child: Text('Convert'),
            ),
            Text(
              _convertedValue != null
                  ? 'Converted Value: $_convertedValue'
                  : '',
            ),
          ],
        ),
      ),
    );
  }

  static double convertLength(double? value, String fromUnit, String toUnit) {
    if (value == null) return 0;
    const conversionRates = {
      'Meter': 1.0,
      'Kilometer': 1000.0,
      'Centimeter': 0.01,
      'Millimeter': 0.001,
    };
    return value * conversionRates[fromUnit]! / conversionRates[toUnit]!;
  }

  static double convertWeight(double? value, String fromUnit, String toUnit) {
    if (value == null) return 0;
    const conversionRates = {
      'Kilogram': 1.0,
      'Gram': 0.001,
      'Pound': 0.453592,
      'Ounce': 0.0283495,
    };
    return value * conversionRates[fromUnit]! / conversionRates[toUnit]!;
  }
}
