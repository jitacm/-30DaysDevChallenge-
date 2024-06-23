import 'package:flutter/material.dart';

class AuthProvider extends ChangeNotifier {
  bool _isAuthenticated = false;

  bool get isAuthenticated => _isAuthenticated;

  void signIn(String email, String password) {
    // Add your sign-in logic here
    _isAuthenticated = true;
    notifyListeners();
  }

  void signUp(String email, String password) {
    // Add your sign-up logic here
    _isAuthenticated = true;
    notifyListeners();
  }

  void signOut() {
    _isAuthenticated = false;
    notifyListeners();
  }
}
