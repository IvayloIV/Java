package List_Utilities;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>() {{
            add(3);
            add(1);
            add(-4);
            add(-242);
        }};

        System.out.println(ListUtils.<Integer>getMin(nums));
        System.out.println(ListUtils.<Integer>getMax(nums));
    }
}
