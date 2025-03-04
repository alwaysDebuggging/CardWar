import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Implement that more people can play and add more in the deck.
        /*

        System.out.println("Please enter how many are playing");
        int nPlayers = numberOfPlayers.nextInt();

        Player[] playerArr = new Player[nPlayers];


        for (int i = 0; i < nPlayers ; i++) {
            System.out.println((i + 1) + " player, enter your name:");
            String nm = playername.nextLine();

            playerArr[i] = new Player(nm);
        }

        for (int i = 0; i < playerArr.length; i++) {
            System.out.println(playerArr[i].name);
        }
         */

        Scanner scanner = new Scanner(System.in);
        Scanner playerOne = new Scanner(System.in);
        Scanner playerTwo = new Scanner(System.in);

        System.out.println("Please enter your name Player 1:");

        Player player1 = new Player(playerOne.next());

        System.out.println("Please enter your name Player 2:");
        Player player2 = new Player(playerTwo.next());

        //The card deck
        Deck deck = new Deck();

        while (deck.getSize() > 0) {
            player1.handCard.add(deck.drawCard());

            if (deck.getSize() > 0) {
                player2.handCard.add(deck.drawCard());
            }
        }

        System.out.println("\n🎴 Welcome to War! 🎴");
        System.out.println("Press ENTER to begin or type 'q' to quit.");

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            System.out.println("Game exited. See you next time!");
            return;
        }

        System.out.println("All the cards are delt");

        System.out.println();

        System.out.println("All the cards are dealt!");
        System.out.println(player1.name + " 1 hand size: " + player1.handCard.size());
        System.out.println(player2.name + " 2 hand size: " + player2.handCard.size());
        System.out.println();

        if (player1.handCard.isEmpty() || player2.handCard.isEmpty()) {
            System.out.println("ERROR: A player has no cards before game starts!");
            return;
        }

        helper(player1, player2, scanner);
        scanner.close();

    }


    public static void helper(Player player1, Player player2, Scanner scanner) {
        int roundCount = 0;

        while (!player1.handCard.isEmpty() || !player1.wonCards.isEmpty() || !player2.handCard.isEmpty() || !player2.wonCards.isEmpty()) {
            roundCount++;

            System.out.println("Press ENTER to play the next round or type 'q' to quit or press s for stats");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Game exited. See you next time!");
                return;
            }





                System.out.println("🔷 Round: " + roundCount + " 🔷");
                if (player1.handCard.isEmpty()) {
                    if (player1.wonCards.isEmpty()) {
                        System.out.println(player2.name + " wins the game since " + player1.name + " has no more cards!");
                        System.out.println();

                        System.out.println(player1.name + " has no more cards on his hands: " +
                                player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and no cards won on the side: " + player1.wonCards.size() + ": " +
                                player1.wonCards.isEmpty());
                        System.out.println();

                        System.out.println(player2.name + " 2's hand " +
                                player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and cards won: " + player2.wonCards.size() + ": " +
                                player2.wonCards.isEmpty());
                        return;
                    }
                    System.out.println("Adding all won cards to" + player1.name + "'s hand...");
                    Collections.shuffle(player1.wonCards);
                    player1.handCard.addAll(player1.wonCards);
                    player1.wonCards.clear();
                }

                if (player2.handCard.isEmpty()) {
                    if (player2.wonCards.isEmpty()) {
                        System.out.println(player1.name + " wins the game since " + player1.name + " has no more cards!");
                        System.out.println();

                        System.out.println(player2.name + " has no more cards on his hands: " +
                                player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and no cards won on the side: " + player2.wonCards.size() + ": " +
                                player2.wonCards.isEmpty());
                        System.out.println();

                        System.out.println(player1.name + "'s hand: " +
                                player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and cards won: " + player1.wonCards.size() + ": " +
                                player1.wonCards.isEmpty());
                        return;
                    }
                    Collections.shuffle(player2.wonCards);
                    player2.handCard.addAll(player2.wonCards);
                    player2.wonCards.clear();
                }


                Card cardFromPlayer1 = player1.handCard.remove(0);
                Card cardFromPlayer2 = player2.handCard.remove(0);


                List<Card> warPile = new ArrayList<>();

                warPile.add(cardFromPlayer1);
                warPile.add(cardFromPlayer2);

                if (cardFromPlayer1.getValue() > cardFromPlayer2.getValue()) {
                    // player 1 card
                    System.out.println(player1.name + " plays " + cardFromPlayer1);
                    System.out.println();

                    // player 2 card
                    System.out.println(player2.name + " plays " + cardFromPlayer2);
                    System.out.println();

                    System.out.println(player1.name + " won a card with " + cardFromPlayer1);
                    System.out.println();

                    player1.wonCards.addAll(warPile);

                } else if (cardFromPlayer1.getValue() < cardFromPlayer2.getValue()) {
                    // player 1 card
                    System.out.println(player1.name + " plays " + cardFromPlayer1);
                    System.out.println();

                    // player 2 card
                    System.out.println(player2.name + " plays " + cardFromPlayer2);
                    System.out.println();

                    System.out.println(player2.name + " won a card with " + cardFromPlayer2);
                    System.out.println();

                    player2.wonCards.addAll(warPile);

                } else if (cardFromPlayer1.getValue() == cardFromPlayer2.getValue()) {
                    while (true) {

                        if (player1.handCard.size() < 3 && player1.wonCards.isEmpty()) {
                            System.out.println(player2.name + " wins because " + player1.name + " doesn’t have enough cards for war!");
                            System.out.println();

                            System.out.println(player2.name + " has no more cards on his hands: " +
                                    player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and no cards won on the side: " + player2.wonCards.size() + ": " +
                                    player2.wonCards.isEmpty());
                            System.out.println();

                            System.out.println(player1.name + "'s hand: " +
                                    player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and cards won: " + player1.wonCards.size() + ": " +
                                    player1.wonCards.isEmpty());
                            return;
                        }
                        if (player2.handCard.size() < 3 && player2.wonCards.isEmpty()) {
                            System.out.println(player1.name + " wins because Player 2 doesn’t have enough cards for war!");
                            System.out.println();

                            System.out.println(player2.name + " has no more cards on his hands: " +
                                    player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and no cards won on the side: " + player2.wonCards.size() + ": " +
                                    player2.wonCards.isEmpty());
                            System.out.println();

                            System.out.println(player1.name + "'s hand: " +
                                    player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and cards won: " + player1.wonCards.size() + ": " +
                                    player1.wonCards.isEmpty());
                            return;
                        }

                        // If a player has won cards but not enough in hand, refill before playing War
                        if (player1.handCard.size() < 3) {
                            Collections.shuffle(player1.wonCards);
                            player1.handCard.addAll(player1.wonCards);
                            player1.wonCards.clear();
                        }
                        if (player2.handCard.size() < 3) {
                            Collections.shuffle(player2.wonCards);
                            player2.handCard.addAll(player2.wonCards);
                            player2.wonCards.clear();
                        }

                        // Ensure they still have enough cards after refilling
                        if (player1.handCard.size() < 3) {
                            System.out.println(player2.name + " wins because " + player1.name + " STILL doesn’t have enough cards after refilling!");
                            System.out.println();

                            System.out.println(player1.name + " has no more cards on his hands: " +
                                    player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and no cards won on the side: " + player1.wonCards.size() + ": " +
                                    player1.wonCards.isEmpty());
                            System.out.println();

                            System.out.println(player2.name + "'s hand " +
                                    player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and cards won: " + player2.wonCards.size() + ": " +
                                    player2.wonCards.isEmpty());
                            return;
                        }
                        if (player2.handCard.size() < 3) {
                            System.out.println(player1.name + "wins because" + player2.name + "STILL doesn’t have enough cards after refilling!");
                            System.out.println();

                            System.out.println(player2.name + " has no more cards on his hands: " +
                                    player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and no cards won on the side: " + player2.wonCards.size() + ": " +
                                    player2.wonCards.isEmpty());
                            System.out.println();

                            System.out.println(player1.name + "'s hand: " +
                                    player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and cards won: " + player1.wonCards.size() + ": " +
                                    player1.wonCards.isEmpty());
                            return;
                        }

                        // Each player places three face-down cards
                        for (int i = 0; i < 3; i++) {
                            if (!player1.handCard.isEmpty()) {
                                warPile.add(player1.handCard.remove(0));
                            }
                            if (!player2.handCard.isEmpty()) {
                                warPile.add(player2.handCard.remove(0));
                            }
                        }

                        System.out.println(player1.name + " plays " + cardFromPlayer1);
                        System.out.println();

                        System.out.println(player2.name + " plays " + cardFromPlayer1);
                        System.out.println();

                        System.out.println("The WAR preparation has BEGUN");
                        System.out.println();

                        System.out.println(player1.name + " and " + player2.name + " removed 3 cards FACE DOWN");
                        System.out.println();

                        // If a player runs out of cards while placing War cards, they lose
                        if (player1.handCard.isEmpty()) {
                            if (!player1.wonCards.isEmpty()) {
                                Collections.shuffle(player1.wonCards);
                                player1.handCard.addAll(player1.wonCards);
                                player1.wonCards.clear();
                            } else {

                                System.out.println(player2.name + " wins because" + player1.name + "ran out of cards while placing War cards!");
                                System.out.println();

                                System.out.println(player1.name + " has no more cards both in his hand: " +
                                        player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and no cards won: " + player1.wonCards.size() + ": " +
                                        player1.wonCards.isEmpty());
                                System.out.println();

                                System.out.println(player2.name + "'s hand " +
                                        player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and cards won: " + player2.wonCards.size() + ": " +
                                        player2.wonCards.isEmpty());
                                return;
                            }
                        }
                        if (player2.handCard.isEmpty()) {
                            if (!player2.wonCards.isEmpty()) {
                                Collections.shuffle(player2.wonCards);
                                player2.handCard.addAll(player2.wonCards);
                                player2.wonCards.clear();
                            } else {

                                System.out.println(player1.name + " wins because" + player2.name + "ran out of cards while placing War cards!");
                                System.out.println();

                                System.out.println(player2.name + "has no more cards both in his hand: " +
                                        player2.handCard.size() + ": " + player2.handCard.isEmpty() + " and no cards won: " + player2.wonCards.size() + ": " +
                                        player2.wonCards.isEmpty());
                                System.out.println();

                                System.out.println(player1.name + "'s hand: " +
                                        player1.handCard.size() + ": " + player1.handCard.isEmpty() + " and cards won: " + player1.wonCards.size() + ": " +
                                        player1.wonCards.isEmpty());

                                return;
                            }

                        }

                        // Each player places their final face-up War card
                        Card finalWarCardP1 = player1.handCard.remove(0);
                        Card finalWarCardP2 = player2.handCard.remove(0);

                        warPile.add(finalWarCardP1);
                        warPile.add(finalWarCardP2);

                        System.out.println("Final War Cards:" + "\n" + player1.name + " plays " + finalWarCardP1 + "\n" + player2.name + " plays " + finalWarCardP2);

                        if (finalWarCardP1.getValue() > finalWarCardP2.getValue()) {
                            System.out.println();
                            System.out.println("🔥 " + player1.name + " wins the war! 🔥");
                            System.out.println();

                            player1.wonCards.addAll(warPile);
                            break;
                        } else if (finalWarCardP1.getValue() < finalWarCardP2.getValue()) {
                            System.out.println();
                            System.out.println("🔥 " + player2.name + " wins the war! 🔥");
                            System.out.println();

                            player2.wonCards.addAll(warPile);
                            break;
                        } else {
                            System.out.println("⚔️ Another WAR! The battle continues...⚔️");

                        }

                    }
                }
            }
        }
    }

