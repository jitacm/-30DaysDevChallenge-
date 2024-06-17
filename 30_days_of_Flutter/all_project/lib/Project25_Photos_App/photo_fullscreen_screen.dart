import 'package:flutter/material.dart';
import 'photo_model.dart';
import 'package:cached_network_image/cached_network_image.dart';

class PhotoFullscreenScreen extends StatelessWidget {
  final Photo photo;

  PhotoFullscreenScreen({required this.photo});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        backgroundColor: Colors.black,
      ),
      body: Center(
        child: CachedNetworkImage(
          imageUrl: photo.url,
          placeholder: (context, url) => Center(child: CircularProgressIndicator()),
          errorWidget: (context, url, error) => Icon(Icons.error),
          fit: BoxFit.contain,
        ),
      ),
    );
  }
}
