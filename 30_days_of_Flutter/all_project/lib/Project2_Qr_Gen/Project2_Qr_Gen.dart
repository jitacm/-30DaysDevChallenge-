import 'package:flutter/material.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:mobile_scanner/mobile_scanner.dart';
import 'package:flutter/services.dart';
import 'package:share/share.dart';




class QR_gen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Home Page',),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _qrData = 'Hello QR';
  final TextEditingController _textController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          QrImageView(
            data: _qrData,
            version: QrVersions.auto,
            size: 250.0,
          ),
          SizedBox(height: 15,),
          Padding(
            padding: const EdgeInsets.all(20.0),
            child: TextField(
              controller: _textController,
              decoration: InputDecoration(
                labelText: 'Enter the data',
                border: OutlineInputBorder(),
              ),
              onSubmitted: (value) => setState(() { _qrData = value; }),
            ),
          ),
          ElevatedButton(
            onPressed: () => setState(() {
              _qrData = _textController.text;
            }),
            child: Text('Generate QR'),
          ),
          ElevatedButton(
            onPressed: () {
              Navigator.of(context).push(MaterialPageRoute(builder: (context) => QRScanPage()));
            },
            child: Text('Scan QR'),
          ),
        ],
      ),
    );
  }
}

class QRScanPage extends StatefulWidget {
  @override
  _QRScanPageState createState() => _QRScanPageState();
}

class _QRScanPageState extends State<QRScanPage> {
  String _scannedCode = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Scan QR'),
      ),
      body: Column(
        children: [
          Expanded(
            child: MobileScanner(
              onDetect: (barcode, args) {
                final String? code = barcode.rawValue;
                if (code != null && code != _scannedCode) {
                  setState(() {
                    _scannedCode = code;
                  });
                  // Optionally vibrate or play a sound.
                }
              },
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
            child: SelectableText('Scanned QR Code: $_scannedCode'),
          ),
          ButtonBar(
            alignment: MainAxisAlignment.center,
            children: [
              TextButton(
                onPressed: _scannedCode.isNotEmpty
                    ? () => _copyToClipboard(_scannedCode)
                    : null,
                child: const Text('Copy'),
              ),
              TextButton(
                onPressed: _scannedCode.isNotEmpty
                    ? () => _shareContent(_scannedCode)
                    : null,
                child: const Text('Share'),
              ),
            ],
          ),
        ],
      ),
    );
  }

  void _copyToClipboard(String text) {
    Clipboard.setData(ClipboardData(text: text)).then((_) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Copied to clipboard!'),
        ),
      );
    });
  }

  void _shareContent(String text) {
    Share.share(text);
  }
}