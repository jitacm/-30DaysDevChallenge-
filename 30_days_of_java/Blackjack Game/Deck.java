import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value = i + 2;
                if (ranks[i].equals("J") || ranks[i].equals("Q") || ranks[i].equals("K")) {
                    value = 10;
                } else if (ranks[i].equals("A")) {
                    value = 11;
                }
                cards.add(new Card(suit, ranks[i], value));
            }
        }

        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.remove(cards.size() - 1);
    }
}
