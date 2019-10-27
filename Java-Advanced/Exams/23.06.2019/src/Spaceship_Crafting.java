import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Spaceship_Crafting {

    public static void main(String[] args) throws IOException {
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> chemicalLiquids = Arrays.stream(bufferedReader.readLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> physicalItems = Arrays.stream(bufferedReader.readLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        int glassCount = 0;
        int aluminiumCount = 0;
        int lithiumCount = 0;
        int carbonFiberCount = 0;

        while (chemicalLiquids.size() != 0 && physicalItems.size() != 0) {
            int sum = chemicalLiquids.get(0) + physicalItems.get(physicalItems.size() - 1);
            boolean find = true;

            switch (sum) {
                case 25:
                    glassCount++;
                    break;
                case 50:
                    aluminiumCount++;
                    break;
                case 75:
                    lithiumCount++;
                    break;
                case 100:
                    carbonFiberCount++;
                    break;
                default:
                    find = false;
                    int n = physicalItems.size() - 1;
                    physicalItems.set(n, physicalItems.get(n) + 3);
            }

            if (find) {
                physicalItems.remove(physicalItems.size() - 1);
            }
            chemicalLiquids.remove(0);
        }

        if (glassCount >= 1 && aluminiumCount >= 1 && lithiumCount >= 1 && carbonFiberCount >= 1) {
            System.out.println("Wohoo! You succeeded in building the spaceship!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to build the spaceship.");
        }


        //Collections.reverse(chemicalLiquids);
        System.out.println("Liquids left: " +
                (chemicalLiquids.size() == 0 ? "none" :
                        chemicalLiquids.stream().map(Object::toString).collect(Collectors.joining(", "))));

        Collections.reverse(physicalItems);
        System.out.println("Physical items left: " +
                (physicalItems.size() == 0 ? "none" :
                        physicalItems.stream().map(Object::toString).collect(Collectors.joining(", "))));

        System.out.println(String.format("Aluminium: %s", aluminiumCount));
        System.out.println(String.format("Carbon fiber: %s", carbonFiberCount));
        System.out.println(String.format("Glass: %s", glassCount));
        System.out.println(String.format("Lithium: %s", lithiumCount));
    }
}
