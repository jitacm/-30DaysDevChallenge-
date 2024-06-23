import 'package:all_project/Project9_Expense_Tracker/expense.dart';
import 'package:all_project/Project9_Expense_Tracker/expense_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class ExpenseScreen extends StatefulWidget {
  @override
  _ExpenseScreenState createState() => _ExpenseScreenState();
}

class _ExpenseScreenState extends State<ExpenseScreen> {
  final _titleController = TextEditingController();
  final _amountController = TextEditingController();

  @override
  void initState() {
    super.initState();
    Provider.of<ExpenseProvider>(context, listen: false).fetchExpenses();
  }

  void _addExpense() {
    final title = _titleController.text;
    final amount = double.tryParse(_amountController.text);

    if (title.isEmpty || amount == null || amount <= 0) {
      return;
    }

    final newExpense = Expense(
      id: 0,  // ID will be set by the database upon insertion
      title: title,
      amount: amount,
      date: DateTime.now(),
    );

    Provider.of<ExpenseProvider>(context, listen: false).addExpense(newExpense);

    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    final expenses = Provider.of<ExpenseProvider>(context).expenses;

    return Scaffold(
      appBar: AppBar(
        title: Text('Expense Tracker'),
      ),
      body: ListView.builder(
        itemCount: expenses.length,
        itemBuilder: (ctx, index) {
          return ListTile(
            title: Text(expenses[index].title),
            subtitle: Text(expenses[index].date.toString()),
            trailing: Text('\$${expenses[index].amount}'),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () {
          showModalBottomSheet(
            context: context,
            builder: (_) {
              return Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: <Widget>[
                    TextField(
                      controller: _titleController,
                      decoration: InputDecoration(labelText: 'Title'),
                    ),
                    TextField(
                      controller: _amountController,
                      decoration: InputDecoration(labelText: 'Amount'),
                      keyboardType: TextInputType.number,
                    ),
                    ElevatedButton(
                      child: Text('Add Expense'),
                      onPressed: _addExpense,
                    ),
                  ],
                ),
              );
            },
          );
        },
      ),
    );
  }
}
