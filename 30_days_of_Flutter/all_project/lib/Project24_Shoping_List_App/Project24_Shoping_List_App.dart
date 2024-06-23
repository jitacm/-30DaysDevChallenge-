import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'shopping_item_provider.dart';
import 'shopping_list_screen.dart';



class ShoppingListApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => ShoppingItemProvider(),
      child: MaterialApp(
        title: 'Shopping List App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: ShoppingListScreen(),
      ),
    );
  }
}
