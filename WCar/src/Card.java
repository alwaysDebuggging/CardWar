public class Card {
    protected int value;
    private final String rank;
    private final String suit;


    public Card(int value, String rank, String suit) {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
