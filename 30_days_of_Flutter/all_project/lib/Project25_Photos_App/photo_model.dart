class Photo {
  final String id;
  final String url;

  Photo({required this.id, required this.url});

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'url': url,
    };
  }

  static Photo fromMap(Map<String, dynamic> map) {
    return Photo(
      id: map['id'],
      url: map['url'],
    );
  }
}
