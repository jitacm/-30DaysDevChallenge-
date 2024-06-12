import 'package:flutter/material.dart';
import 'package:fl_chart/fl_chart.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class CryptoDetail extends StatefulWidget {
  final String id;
  final String name;

  CryptoDetail({required this.id, required this.name});

  @override
  _CryptoDetailState createState() => _CryptoDetailState();
}

class _CryptoDetailState extends State<CryptoDetail> {
  List<FlSpot> _graphData = [];

  @override
  void initState() {
    super.initState();
    _fetchGraphData();
  }

  Future<void> _fetchGraphData() async {
    final response = await http.get(Uri.parse(
        'https://min-api.cryptocompare.com/data/v2/histoday?fsym=${widget.id}&tsym=USD&limit=30'));
    if (response.statusCode == 200) {
      final data = json.decode(response.body)['Data']['Data'];
      setState(() {
        _graphData = data.map<FlSpot>((point) {
          return FlSpot(point['time'].toDouble(), point['close'].toDouble());
        }).toList();
      });
    } else {
      throw Exception('Failed to load graph data');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.name),
      ),
      body: _graphData.isEmpty
          ? Center(child: CircularProgressIndicator())
          : Padding(
        padding: const EdgeInsets.all(8.0),
        child: LineChart(
          LineChartData(
            gridData: FlGridData(show: true),
            titlesData: FlTitlesData(show: true),
            borderData: FlBorderData(show: true),
            lineBarsData: [
              LineChartBarData(
                spots: _graphData,
                isCurved: true,
                colors: [Colors.blue],
                barWidth: 3,
                isStrokeCapRound: true,
                dotData: FlDotData(show: false),
                belowBarData: BarAreaData(show: false),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
