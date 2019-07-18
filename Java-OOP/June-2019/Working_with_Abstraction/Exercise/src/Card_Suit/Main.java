package Card_Suit;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Suit> suits = new ArrayList<>();
        suits.add(Suit.CLUBS);
        suits.add(Suit.DIAMONDS);
        suits.add(Suit.HEARTS);
        suits.add(Suit.SPADES);

        System.out.println("Card Suits:");
        for (Suit suit : suits) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s", suit.getValue(), suit));
        }
    }
}
