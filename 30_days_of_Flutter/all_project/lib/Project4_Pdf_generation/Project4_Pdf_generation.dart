import 'package:flutter/material.dart';
import 'package:pdf/pdf.dart';
import 'package:pdf/widgets.dart' as pw;
import 'package:printing/printing.dart';



class Pdf extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'PDF Printing Example',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: PdfPrintingScreen(),
    );
  }
}

class PdfPrintingScreen extends StatelessWidget {
  // Function to create a PDF document
  pw.Document _generatePdf() {
    final pdf = pw.Document();

    // Add content to the PDF
    pdf.addPage(
      pw.Page(
        build: (context) {
          return pw.Center(
            child: pw.Text('Hello, World!', style: pw.TextStyle(fontSize: 40.0)),
          );
        },
      ),
    );

    return pdf;
  }

  // Function to print the generated PDF
  Future<void> _printPdf(pw.Document pdf) async {
    // Send the PDF document to the printer
    await Printing.layoutPdf(onLayout: (format) => pdf.save());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('PDF Printing Example'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: () {
                // Generate the PDF document
                final pdf = _generatePdf();
                // Print the generated PDF document
                _printPdf(pdf);
              },
              child: Text('Generate and Print PDF'),
            ),
          ],
        ),
      ),
    );
  }
}
