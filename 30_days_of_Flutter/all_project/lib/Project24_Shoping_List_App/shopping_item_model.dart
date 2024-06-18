class ShoppingItem {
  String id;
  String name;
  String category;
  int quantity;

  ShoppingItem({
    required this.id,
    required this.name,
    required this.category,
    required this.quantity,
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'category': category,
      'quantity': quantity,
    };
  }

  static ShoppingItem fromMap(Map<String, dynamic> map) {
    return ShoppingItem(
      id: map['id'],
      name: map['name'],
      category: map['category'],
      quantity: map['quantity'],
    );
  }
}
