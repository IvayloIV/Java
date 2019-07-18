package Cards_With_Power;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        int power = this.calculatePower();
        return String.format("Card name: %s of %s; Card power: %d", this.rank, this.suit, power);
    }

    private int calculatePower() {
        return this.rank.getValue() + this.suit.getValue();
    }
}
