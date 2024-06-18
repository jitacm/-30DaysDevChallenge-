import 'package:all_project/Project10_Contact_App/contact_screen.dart';
import 'package:all_project/Project11_Quiz_App/Project11_Quiz_App.dart';
import 'package:all_project/Project12_Teanslator_App/Project12_Teanslator_App.dart';
import 'package:all_project/Project14_Unit_Convertor/Project14_Unit_Convertor.dart';
import 'package:all_project/Project19_Pedometer/Project19_Pedometer.dart';
import 'package:all_project/Project1_SplashScreen/Project1_SplashScreen.dart';
import 'package:all_project/Project20_Expense_Splitter/Project20_Expense_Splitter.dart';
import 'package:all_project/Project21_TODO_App/Project21_TODO_App.dart';
import 'package:all_project/Project23_Note_Taking_App/Project23_Note_Taking_App.dart';
import 'package:all_project/Project24_Shoping_List_App/Project24_Shoping_List_App.dart';
import 'package:all_project/Project26_Movei_App/Project26_Movei_App.dart';
import 'package:all_project/Project2_Qr_Gen/Project2_Qr_Gen.dart';
import 'package:all_project/Project3_CryptoPrice/Project3_CryptoPrice.dart';
import 'package:all_project/Project4_Pdf_generation/Project4_Pdf_generation.dart';
import 'package:all_project/Project5_Weather_App/Project5_Weather_App.dart';
import 'package:all_project/Project6_Bmi_Calculartor/Project6_Bmi_Calculartor.dart';
import 'package:all_project/Project7_Music_Player/Project7_Music_Player.dart';
import 'package:all_project/Project8_Calender_App/Project8_Calender_App.dart';
import 'package:all_project/Project9_Expense_Tracker/Project9_Expense_Tracker.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(),
      home: MovieDatabaseApp(),
    );
  }
}
