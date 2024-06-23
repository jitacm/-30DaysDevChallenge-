import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';
import 'shopping_item_model.dart';

class ShoppingItemProvider with ChangeNotifier {
  List<ShoppingItem> _items = [];

  List<ShoppingItem> get items => _items;

  ShoppingItemProvider() {
    _loadItems();
  }

  Future<void> _loadItems() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? itemsString = prefs.getString('items');
    if (itemsString != null) {
      List<dynamic> itemsJson = json.decode(itemsString);
      _items = itemsJson.map((json) => ShoppingItem.fromMap(json)).toList();
      notifyListeners();
    }
  }

  Future<void> _saveItems() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String> itemsJson = _items.map((item) => json.encode(item.toMap())).toList();
    await prefs.setString('items', json.encode(itemsJson));
  }

  void addItem(ShoppingItem item) {
    _items.add(item);
    _saveItems();
    notifyListeners();
  }

  void updateItem(ShoppingItem item) {
    int index = _items.indexWhere((i) => i.id == item.id);
    if (index != -1) {
      _items[index] = item;
      _saveItems();
      notifyListeners();
    }
  }

  void deleteItem(String id) {
    _items.removeWhere((item) => item.id == id);
    _saveItems();
    notifyListeners();
  }
}
