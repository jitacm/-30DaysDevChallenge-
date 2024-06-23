import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'auth_provider.dart';
import 'signin_page.dart';
import 'signup_page.dart';



class Signup extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => AuthProvider(),
      child: MaterialApp(
        title: 'Flutter Auth Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: SignInPage(),
      ),
    );
  }
}
