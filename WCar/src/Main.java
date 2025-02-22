import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Player player1 = new Player();
        Player player2 = new Player();
        Deck deck = new Deck();

        while (deck.getSize() > 0) {
            player1.handCard.add(deck.drawCard());

            if (deck.getSize() > 0) {
                player2.handCard.add(deck.drawCard());
            }
        }
        System.out.println("All the cards are delt");
        System.out.println();

        System.out.println("All the cards are dealt!");
        System.out.println("Player 1 hand size: " + player1.handCard.size());
        System.out.println("Player 2 hand size: " + player2.handCard.size());
        System.out.println();

        if (player1.handCard.isEmpty() || player2.handCard.isEmpty()) {
            System.out.println("ERROR: A player has no cards before game starts!");
            return;
        }

        helper(player1, player2);

    }

//    public static void h (){
//        if(player1.handCard.isEmpty() && player1.wonCards.isEmpty()){
//            System.out.println();
//            System.out.println("Player 1 has no more cards. GGs Player 2 has won this round.");
//            System.out.println();
//
//            System.out.println("Info on player 1: " + "list of card in hand " + player1.handCard.size() + " list of won cards " + player1.handCard.size());
//            return;
//        } else if (player1.handCard.isEmpty()) {
//            System.out.println();
//            System.out.println("Adding all the won cards to the hand Deck");
//
//            if (!player1.wonCards.isEmpty()) {
//                Collections.shuffle(player1.wonCards);
//                player1.handCard.addAll(player1.wonCards);
//                player1.wonCards.clear();
//            }
//
//        }
//
//        if(player2.handCard.isEmpty() && player2.wonCards.isEmpty()){
//            System.out.println();
//            System.out.println("Player 2 has no more cards. GGs Player 1 has won this round.");
//            System.out.println();
//
//            System.out.println("Info on player 2: " + "list of card in hand " + player2.handCard.size() + " list of won cards " + player2.wonCards.size());
//
//            return;
//        } else if (player2.handCard.isEmpty()){
//            System.out.println();
//            System.out.println("Adding all the won cards to the hand Deck");
//
//            // making sure it shuffled and clear after inserting to the hand
//            if (!player2.wonCards.isEmpty()) {
//                Collections.shuffle(player2.wonCards);
//                player2.handCard.addAll(player2.wonCards);
//                player2.wonCards.clear();
//            }
//
//        }
//    }


    public static void helper(Player player1, Player player2) {
        while (!player1.handCard.isEmpty() || !player1.wonCards.isEmpty() || !player2.handCard.isEmpty() || !player2.wonCards.isEmpty()) {

            if (player1.handCard.isEmpty()) {
                if (player1.wonCards.isEmpty()) {
                    System.out.println("Player 2 wins the game since Player 1 has no more cards!");
                    return;
                }
                System.out.println("Adding all won cards to Player 1's hand...");
                Collections.shuffle(player1.wonCards);
                player1.handCard.addAll(player1.wonCards);
                player1.wonCards.clear();
            }

            if (player2.handCard.isEmpty()) {
                if (player2.wonCards.isEmpty()) {
                    System.out.println("Player 2 wins the game since Player 1 has no more cards!");
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
                System.out.println("p1 " + cardFromPlayer1);
                System.out.println();

                // player 2 card
                System.out.println("p2 " + cardFromPlayer2);
                System.out.println();

                System.out.println("Player 1 won a card with " + cardFromPlayer1);
                System.out.println();

                player1.wonCards.addAll(warPile);

            } else if (cardFromPlayer1.getValue() < cardFromPlayer2.getValue()) {
                // player 1 card
                System.out.println("p1 " + cardFromPlayer1);
                System.out.println();

                // player 2 card
                System.out.println("p2 " + cardFromPlayer2);
                System.out.println();

                System.out.println("Player 2 won a card with " + cardFromPlayer2);
                System.out.println();

                player2.wonCards.addAll(warPile);

            } else if (cardFromPlayer1.getValue() == cardFromPlayer2.getValue()) {
                while (true) {
                    if (player1.handCard.size() < 3 && player1.wonCards.isEmpty()) {
                        System.out.println("Player 2 wins because Player 1 doesn’t have enough cards for war!");
                        return;
                    }
                    if (player2.handCard.size() < 3 && player2.wonCards.isEmpty()) {
                        System.out.println("Player 1 wins because Player 2 doesn’t have enough cards for war!");
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
                        System.out.println("Player 2 wins because Player 1 STILL doesn’t have enough cards after refilling!");
                        return;
                    }
                    if (player2.handCard.size() < 3) {
                        System.out.println("Player 1 wins because Player 2 STILL doesn’t have enough cards after refilling!");
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

                    System.out.println("Player 1 and Player 2 removed 3 cards FACE DOWN");

                    // If a player runs out of cards while placing War cards, they lose
                    if (player1.handCard.isEmpty()) {
                        System.out.println("Player 2 wins because Player 1 ran out of cards while placing War cards!");
                        System.out.println();

                        System.out.println("Player 1 has no more cards both in his hand " +
                                player1.handCard.size() + " " + player1.handCard.isEmpty() + " and no cards won " + player1.wonCards.size() + " " +
                                player1.wonCards.isEmpty());
                        System.out.println();

                        System.out.println("Player 2's hand " +
                                player2.handCard.size() + " " + player2.handCard.isEmpty() + " and cards won " + player2.wonCards.size() +" " +
                                player2.wonCards.isEmpty());
                        return;
                    }
                    if (player2.handCard.isEmpty()) {
                        System.out.println("Player 1 wins because Player 2 ran out of cards while placing War cards!");
                        System.out.println();

                        System.out.println("Player 2 has no more cards both in his hand " +
                                player2.handCard.size() + " " + player2.handCard.isEmpty() + " and no cards won " + player2.wonCards.size() + " " +
                                player2.wonCards.isEmpty());
                        System.out.println();

                        System.out.println("Player 1's hand " +
                                player1.handCard.size() + " " + player1.handCard.isEmpty() + " and cards won " + player1.wonCards.size() + " " +
                                player1.wonCards.isEmpty());

                        return;
                    }

                    // Each player places their final face-up War card
                    Card finalWarCardP1 = player1.handCard.remove(0);
                    Card finalWarCardP2 = player2.handCard.remove(0);

                    warPile.add(finalWarCardP1);
                    warPile.add(finalWarCardP2);

                    System.out.println("Final War Cards: Player 1 -> " + finalWarCardP1 + " | Player 2 -> " + finalWarCardP2);

                    if (finalWarCardP1.getValue() > finalWarCardP2.getValue()) {
                        System.out.println();
                        System.out.println("🔥 Player 1 wins the war!");
                        player1.wonCards.addAll(warPile);
                        break;
                    } else if (finalWarCardP1.getValue() < finalWarCardP2.getValue()) {
                        System.out.println();
                        System.out.println("🔥 Player 2 wins the war!");
                        player2.wonCards.addAll(warPile);
                        break;
                    } else {
                        System.out.println("⚔️ Another WAR! The battle continues...");

                    }

                }
            }
        }
    }
}
