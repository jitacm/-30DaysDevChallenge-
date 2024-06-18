import 'package:flutter/material.dart';
import 'photo_model.dart';

class PhotoProvider with ChangeNotifier {
  List<Photo> _photos = [];

  List<Photo> get photos => _photos;

  PhotoProvider() {
    _loadPhotos();
  }

  void _loadPhotos() {
    // Sample data
    _photos = List.generate(
      20,
          (index) => Photo(
        id: index.toString(),
        url: 'https://picsum.photos/200?random=$index',
      ),
    );
    notifyListeners();
  }
}
