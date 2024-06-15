import 'package:all_project/Project12_Teanslator_App/translation_provider.dart';
import 'package:all_project/Project12_Teanslator_App/translation_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class Translator extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => TranslationProvider(),
      child: MaterialApp(
        title: 'Language Translator',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: TranslationScreen(),
      ),
    );
  }
}

