import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'shopping_item_model.dart';
import 'shopping_item_provider.dart';
import 'shopping_item_edit_screen.dart';

class ShoppingListScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Shopping List'),
      ),
      body: Consumer<ShoppingItemProvider>(
        builder: (context, itemProvider, child) {
          return ListView.builder(
            itemCount: itemProvider.items.length,
            itemBuilder: (context, index) {
              ShoppingItem item = itemProvider.items[index];
              return ListTile(
                title: Text(item.name),
                subtitle: Text('${item.category} - Quantity: ${item.quantity}'),
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ShoppingItemEditScreen(item: item),
                    ),
                  );
                },
                trailing: IconButton(
                  icon: Icon(Icons.delete),
                  onPressed: () {
                    itemProvider.deleteItem(item.id);
                  },
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => ShoppingItemEditScreen(),
            ),
          );
        },
        child: Icon(Icons.add),
      ),
    );
  }
}
