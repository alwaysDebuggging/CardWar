public class Card {
    protected int valuee;
    public enum Suits {Hearts, Spade, Diamond, Clubs};
    private Suits suit;


    public Card(int valuee, Suits suit){
        this.valuee = valuee;
        this.suit = suit;
    }


    public int getValue() {
        return valuee;
    }


    public Suits getSuit() {
        return suit;
    }

}
