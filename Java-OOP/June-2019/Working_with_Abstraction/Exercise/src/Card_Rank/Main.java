package Card_Rank;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.ACE);
        ranks.add(Rank.TWO);
        ranks.add(Rank.THREE);
        ranks.add(Rank.FOUR);
        ranks.add(Rank.FIVE);
        ranks.add(Rank.SIX);
        ranks.add(Rank.SEVEN);
        ranks.add(Rank.EIGHT);
        ranks.add(Rank.NINE);
        ranks.add(Rank.TEN);
        ranks.add(Rank.JACK);
        ranks.add(Rank.QUEEN);
        ranks.add(Rank.KING);

        System.out.println("Card Ranks:");
        for (Rank rank : ranks) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s", rank.getValue(), rank));
        }
    }
}