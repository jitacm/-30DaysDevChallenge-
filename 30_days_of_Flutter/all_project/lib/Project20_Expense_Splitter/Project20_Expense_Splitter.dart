import 'package:flutter/material.dart';


class ExpenseSplitterApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Expense Splitter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: ExpenseSplitterHomePage(),
    );
  }
}

class ExpenseSplitterHomePage extends StatefulWidget {
  @override
  _ExpenseSplitterHomePageState createState() => _ExpenseSplitterHomePageState();
}

class _ExpenseSplitterHomePageState extends State<ExpenseSplitterHomePage> {
  final List<String> _friends = [];
  final List<double> _expenses = [];
  final TextEditingController _friendController = TextEditingController();
  final TextEditingController _expenseController = TextEditingController();

  void _addFriend() {
    if (_friendController.text.isNotEmpty) {
      setState(() {
        _friends.add(_friendController.text);
      });
      _friendController.clear();
    }
  }

  void _addExpense() {
    if (_expenseController.text.isNotEmpty) {
      setState(() {
        _expenses.add(double.parse(_expenseController.text));
      });
      _expenseController.clear();
    }
  }

  double _calculateTotalExpense() {
    return _expenses.fold(0, (sum, item) => sum + item);
  }

  double _calculateSharePerPerson() {
    if (_friends.isEmpty) return 0;
    return _calculateTotalExpense() / _friends.length;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Expense Splitter'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            TextField(
              controller: _friendController,
              decoration: InputDecoration(labelText: 'Add Friend'),
            ),
            ElevatedButton(
              onPressed: _addFriend,
              child: Text('Add Friend'),
            ),
            SizedBox(height: 20),
            TextField(
              controller: _expenseController,
              decoration: InputDecoration(labelText: 'Add Expense'),
              keyboardType: TextInputType.number,
            ),
            ElevatedButton(
              onPressed: _addExpense,
              child: Text('Add Expense'),
            ),
            SizedBox(height: 20),
            Text(
              'Total Expense: \$${_calculateTotalExpense().toStringAsFixed(2)}',
              style: TextStyle(fontSize: 18),
            ),
            Text(
              'Share per Person: \$${_calculateSharePerPerson().toStringAsFixed(2)}',
              style: TextStyle(fontSize: 18),
            ),
            Expanded(
              child: ListView.builder(
                itemCount: _friends.length,
                itemBuilder: (context, index) {
                  return ListTile(
                    title: Text(_friends[index]),
                    subtitle: Text('Owes: \$${_calculateSharePerPerson().toStringAsFixed(2)}'),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _friendController.dispose();
    _expenseController.dispose();
    super.dispose();
  }
}
