import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'photo_model.dart';
import 'photo_provider.dart';
import 'photo_fullscreen_screen.dart';
import 'package:cached_network_image/cached_network_image.dart';

class PhotoGridScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Photo Gallery'),
      ),
      body: Consumer<PhotoProvider>(
        builder: (context, photoProvider, child) {
          return GridView.builder(
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 3,
              crossAxisSpacing: 4.0,
              mainAxisSpacing: 4.0,
            ),
            itemCount: photoProvider.photos.length,
            itemBuilder: (context, index) {
              Photo photo = photoProvider.photos[index];
              return GestureDetector(
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => PhotoFullscreenScreen(photo: photo),
                    ),
                  );
                },
                child: CachedNetworkImage(
                  imageUrl: photo.url,
                  placeholder: (context, url) => Center(child: CircularProgressIndicator()),
                  errorWidget: (context, url, error) => Icon(Icons.error),
                  fit: BoxFit.cover,
                ),
              );
            },
          );
        },
      ),
    );
  }
}
