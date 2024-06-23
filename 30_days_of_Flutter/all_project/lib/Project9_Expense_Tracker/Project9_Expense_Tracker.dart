import 'package:all_project/Project9_Expense_Tracker/expense_provider.dart';
import 'package:all_project/Project9_Expense_Tracker/expense_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';



class Expences extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => ExpenseProvider(),
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Expense Tracker',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: ExpenseScreen(),
      ),
    );
  }
}



