This Python script implements a simple console-based Blackjack game using basic object-oriented programming concepts and text-based UI. Players compete against a dealer to achieve a hand value as close to 21 as possible without exceeding it. The game involves dealing cards from a standard deck, calculating scores, and handling user decisions like hitting or standing. The dealer follows fixed rules for drawing additional cards, creating a balanced and interactive game experience.

**Features:**

Card Representation: Cards are represented using a Card class, which includes card faces (e.g., 'A', '2', '3', etc.), values, and symbols for each suit.
Game Play: Players start with two cards and can choose to hit (draw additional cards) or stand (keep their current hand). The dealer also plays by specific rules.
Score Calculation: Handles the adjustment of scores for Aces (value can be 1 or 11) and updates scores dynamically as cards are dealt.
Card Display: Displays cards in a text-based format that shows both visible and hidden cards for the dealer.
Game Outcome: Determines the winner based on the final scores, handling cases where either the player or dealer busts or achieves a Blackjack.