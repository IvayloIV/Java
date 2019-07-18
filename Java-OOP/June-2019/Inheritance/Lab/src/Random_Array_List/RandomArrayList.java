package Random_Array_List;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList {
    public Random random;

    public RandomArrayList() {
        random = new Random();
    }

    public Object getRandomElement() {
        int index = this.random.nextInt(this.size());
        return this.remove(index);
    }
}
