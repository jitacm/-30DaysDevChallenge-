import 'package:all_project/Project10_Contact_App/Project10_Contact_App.dart';
import 'package:all_project/Project10_Contact_App/database_helper1.dart';
import 'package:flutter/foundation.dart';

class ContactProvider with ChangeNotifier {
  List<Contact> _contacts = [];

  List<Contact> get contacts => _contacts;

  Future<void> addContact(Contact contact) async {
    final newContact = await DatabaseHelper.instance.create(contact);
    _contacts.add(newContact);
    notifyListeners();
  }

  Future<void> fetchContacts() async {
    _contacts = await DatabaseHelper.instance.readAllContacts();
    notifyListeners();
  }
}
