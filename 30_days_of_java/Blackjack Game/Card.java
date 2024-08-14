public class Card {
    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public void printCard() {
        String topBottomBorder = "+-----+";
        String rankLine = "| " + (rank.length() > 1 ? rank : " " + rank) + "   |";
        String suitLine = "|  " + getSuitSymbol() + "  |";
        String bottomLine = "+-----+";

        System.out.println(topBottomBorder);
        System.out.println(rankLine);
        System.out.println(suitLine);
        System.out.println(bottomLine);
    }

    private String getSuitSymbol() {
        switch (suit) {
            case "Hearts":
                return "♥";
            case "Diamonds":
                return "♦";
            case "Clubs":
                return "♣";
            case "Spades":
                return "♠";
            default:
                return " ";
        }
    }
}
