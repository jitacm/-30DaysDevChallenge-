import 'package:all_project/Project9_Expense_Tracker/database_helper.dart';
import 'package:all_project/Project9_Expense_Tracker/expense.dart';
import 'package:flutter/foundation.dart';


class ExpenseProvider with ChangeNotifier {
  List<Expense> _expenses = [];

  List<Expense> get expenses => _expenses;

  Future<void> addExpense(Expense expense) async {
    final newExpense = await DatabaseHelper.instance.create(expense);
    _expenses.add(newExpense);
    notifyListeners();
  }

  Future<void> fetchExpenses() async {
    _expenses = await DatabaseHelper.instance.readAllExpenses();
    notifyListeners();
  }
}
