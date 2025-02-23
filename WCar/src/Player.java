import java.util.ArrayList;
import java.util.List;

public class Player {
    List<Card> handCard = new ArrayList<>();
    List<Card> wonCards = new ArrayList<>();
    protected String name;


    public Player (String name){
        this.name = name;

    }


}
