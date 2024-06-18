
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'shopping_item_model.dart';
import 'shopping_item_provider.dart';
import 'package:uuid/uuid.dart';

class ShoppingItemEditScreen extends StatefulWidget {
  final ShoppingItem? item;

  ShoppingItemEditScreen({this.item});

  @override
  _ShoppingItemEditScreenState createState() => _ShoppingItemEditScreenState();
}

class _ShoppingItemEditScreenState extends State<ShoppingItemEditScreen> {
  final _nameController = TextEditingController();
  final _categoryController = TextEditingController();
  final _quantityController = TextEditingController();
  final _uuid = Uuid();

  @override
  void initState() {
    super.initState();
    if (widget.item != null) {
      _nameController.text = widget.item!.name;
      _categoryController.text = widget.item!.category;
      _quantityController.text = widget.item!.quantity.toString();
    }
  }

  void _saveItem() {
    if (_nameController.text.isEmpty || _categoryController.text.isEmpty || _quantityController.text.isEmpty) {
      return;
    }

    ShoppingItem item = ShoppingItem(
      id: widget.item?.id ?? _uuid.v4(),
      name: _nameController.text,
      category: _categoryController.text,
      quantity: int.parse(_quantityController.text),
    );

    if (widget.item == null) {
      Provider.of<ShoppingItemProvider>(context, listen: false).addItem(item);
    } else {
      Provider.of<ShoppingItemProvider>(context, listen: false).updateItem(item);
    }

    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.item == null ? 'New Item' : 'Edit Item'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _nameController,
              decoration: InputDecoration(labelText: 'Name'),
            ),
            TextField(
              controller: _categoryController,
              decoration: InputDecoration(labelText: 'Category'),
            ),
            TextField(
              controller: _quantityController,
              decoration: InputDecoration(labelText: 'Quantity'),
              keyboardType: TextInputType.number,
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _saveItem,
              child: Text('Save Item'),
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _nameController.dispose();
    _categoryController.dispose();
    _quantityController.dispose();
    super.dispose();
  }
}
