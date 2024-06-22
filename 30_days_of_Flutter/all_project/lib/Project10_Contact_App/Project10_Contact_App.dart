import 'package:all_project/Project10_Contact_App/contact_provider.dart';
import 'package:all_project/Project10_Contact_App/contact_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';




class Contact extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => ContactProvider(),
      child: MaterialApp(
        title: 'Contact List',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: ContactScreen(),
      ),
    );
  }
}
