import 'dart:async';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';



class MemoryGameApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => GameState(),
      child: MaterialApp(
        title: 'Memory Game',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: MemoryGameHomePage(),
      ),
    );
  }
}

class MemoryGameHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Memory Game'),
      ),
      body: Column(
        children: [
          DifficultySelector(),
          Expanded(child: MemoryGameBoard()),
        ],
      ),
    );
  }
}

class DifficultySelector extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        ElevatedButton(
          onPressed: () => context.read<GameState>().startGame(4),
          child: Text('Easy'),
        ),
        SizedBox(width: 16),
        ElevatedButton(
          onPressed: () => context.read<GameState>().startGame(6),
          child: Text('Medium'),
        ),
        SizedBox(width: 16),
        ElevatedButton(
          onPressed: () => context.read<GameState>().startGame(8),
          child: Text('Hard'),
        ),
      ],
    );
  }
}

class MemoryGameBoard extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Consumer<GameState>(
      builder: (context, gameState, child) {
        return GridView.builder(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: gameState.gridSize,
            childAspectRatio: 1.0,
          ),
          itemCount: gameState.cards.length,
          itemBuilder: (context, index) {
            final card = gameState.cards[index];
            return GestureDetector(
              onTap: () => gameState.selectCard(card),
              child: Card(
                color: card.isMatched || card.isSelected
                    ? Colors.white
                    : Colors.blue,
                child: Center(
                  child: Text(
                    card.isMatched || card.isSelected ? card.value : '',
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.black,
                    ),
                  ),
                ),
              ),
            );
          },
        );
      },
    );
  }
}

class GameState extends ChangeNotifier {
  List<MemoryCard> cards = [];
  MemoryCard? selectedCard;
  int gridSize = 4;

  void startGame(int size) {
    gridSize = size;
    cards = List.generate(size * size ~/ 2, (index) {
      final value = (index + 1).toString();
      return [MemoryCard(value: value), MemoryCard(value: value)];
    }).expand((pair) => pair).toList();
    cards.shuffle();
    selectedCard = null;
    notifyListeners();
  }

  void selectCard(MemoryCard card) {
    if (card.isMatched || card.isSelected) return;

    card.isSelected = true;
    notifyListeners();

    if (selectedCard == null) {
      selectedCard = card;
    } else {
      if (selectedCard!.value == card.value) {
        card.isMatched = true;
        selectedCard!.isMatched = true;
      } else {
        Timer(Duration(seconds: 1), () {
          card.isSelected = false;
          selectedCard!.isSelected = false;
          selectedCard = null;
          notifyListeners();
        });
      }
      selectedCard = null;
    }
    notifyListeners();
  }
}

class MemoryCard {
  final String value;
  bool isSelected = false;
  bool isMatched = false;

  MemoryCard({required this.value});
}
