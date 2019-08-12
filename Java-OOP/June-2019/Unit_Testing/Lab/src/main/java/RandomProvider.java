import java.util.Random;

public class RandomProvider {
    private Random random;

    public RandomProvider() {
        random = new Random();
    }

    int nextInt(int maxValue) {
        return this.random.nextInt(maxValue);
    }
}
