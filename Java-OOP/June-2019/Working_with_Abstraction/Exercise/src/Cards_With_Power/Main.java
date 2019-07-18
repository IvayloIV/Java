package Cards_With_Power;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Rank rank = Rank.valueOf(bufferedReader.readLine());
        Suit suit = Suit.valueOf(bufferedReader.readLine());

        Card card = new Card(rank, suit);
        System.out.println(card);
    }
}
