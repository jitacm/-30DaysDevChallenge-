import 'package:flutter/material.dart';
import 'package:audioplayers/audioplayers.dart';


class Music extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Music Player',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MusicPlayer(),
    );
  }
}

class MusicPlayer extends StatefulWidget {
  @override
  _MusicPlayerState createState() => _MusicPlayerState();
}

class _MusicPlayerState extends State<MusicPlayer> {
  AudioPlayer _audioPlayer = AudioPlayer();
  bool isPlaying = false;
  bool isPaused = false;
  String currentSong = 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3';

  @override
  void dispose() {
    _audioPlayer.dispose();
    super.dispose();
  }

  void playMusic() async {
    int result = await _audioPlayer.play(currentSong);
    if (result == 1) {
      setState(() {
        isPlaying = true;
        isPaused = false;
      });
    }
  }

  void pauseMusic() async {
    int result = await _audioPlayer.pause();
    if (result == 1) {
      setState(() {
        isPlaying = false;
        isPaused = true;
      });
    }
  }

  void stopMusic() async {
    int result = await _audioPlayer.stop();
    if (result == 1) {
      setState(() {
        isPlaying = false;
        isPaused = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Music Player'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: isPlaying ? null : playMusic,
              child: Text('Play'),
            ),
            ElevatedButton(
              onPressed: isPlaying ? pauseMusic : null,
              child: Text('Pause'),
            ),
            ElevatedButton(
              onPressed: isPlaying || isPaused ? stopMusic : null,
              child: Text('Stop'),
            ),
          ],
        ),
      ),
    );
  }
}
