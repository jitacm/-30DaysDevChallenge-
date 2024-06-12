import 'package:all_project/Project1_SplashScreen/Project1_SplashScreen.dart';
import 'package:all_project/Project2_Qr_Gen/Project2_Qr_Gen.dart';
import 'package:all_project/Project3_CryptoPrice/Project3_CryptoPrice.dart';
import 'package:all_project/Project4_Pdf_generation/Project4_Pdf_generation.dart';
import 'package:all_project/Project5_Weather_App/Project5_Weather_App.dart';
import 'package:all_project/Project6_Bmi_Calculartor/Project6_Bmi_Calculartor.dart';
import 'package:all_project/Project7_Music_Player/Project7_Music_Player.dart';
import 'package:all_project/Project8_Calender_App/Project8_Calender_App.dart';
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
      home: Calender(),
    );
  }
}
