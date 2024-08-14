import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private Scanner scanner;

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Blackjack!");

        // Initial dealing
        playerHand.add(deck.dealCard());
        dealerHand.add(deck.dealCard());
        playerHand.add(deck.dealCard());
        dealerHand.add(deck.dealCard());

        // Player's turn
        boolean playerBusted = playerTurn();
        if (!playerBusted) {
            // Dealer's turn
            dealerTurn();
        }

        // Determine the outcome
        determineOutcome(playerBusted);
    }

    private boolean playerTurn() {
        boolean playerBusted = false;
        while (true) {
            printHands(true);
            System.out.println("Do you want to (h)it or (s)tand?");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("h")) {
                playerHand.add(deck.dealCard());
                if (getHandValue(playerHand) > 21) {
                    printHands(true);
                    System.out.println("You bust!");
                    playerBusted = true;
                    break;
                }
            } else if (choice.equals("s")) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'h' to hit or 's' to stand.");
            }
        }
        return playerBusted;
    }

    private void dealerTurn() {
        System.out.println("Dealer's hand:");
        printHand(dealerHand, true);
        while (getHandValue(dealerHand) < 17) {
            dealerHand.add(deck.dealCard());
            printHand(dealerHand, true);
        }
        if (getHandValue(dealerHand) > 21) {
            System.out.println("Dealer busts!");
        }
    }

    private void determineOutcome(boolean playerBusted) {
        int playerScore = getHandValue(playerHand);
        int dealerScore = getHandValue(dealerHand);

        if (playerBusted) {
            System.out.println("Dealer wins!");
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private int getHandValue(List<Card> hand) {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getValue();
            if (card.getRank().equals("A")) {
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    private void printHands(boolean revealDealerHand) {
        System.out.println("Your hand:");
        printHand(playerHand, false);
        System.out.println("Dealer's hand:");
        if (revealDealerHand) {
            printHand(dealerHand, true);
        } else {
            // Only reveal the first card of the dealer
            System.out.println(" [Hidden]");
            System.out.println(dealerHand.get(0));
        }
    }

    private void printHand(List<Card> hand, boolean fullHand) {
        for (Card card : hand) {
            card.printCard();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.startGame();
    }
}
