import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final String [] cranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    private final String [] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
    private final int [] cvalue = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public Deck (){
        for (int i = 0; i < suits.length ; i++) {
            for (int j = 0; j < cranks.length; j++) {
                cards.add(new Card(cvalue[j], cranks[j], suits[i]));
            }
        }
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public int getSize() {
        return cards.size();
    }


}
