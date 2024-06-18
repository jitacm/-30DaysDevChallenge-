import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'photo_provider.dart';
import 'photo_grid_screen.dart';




class PhotoGalleryApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => PhotoProvider(),
      child: MaterialApp(
        title: 'Photo Gallery App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: PhotoGridScreen(),
      ),
    );
  }
}
