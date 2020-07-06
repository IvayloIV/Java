import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LootBox {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Integer> firstSeq = Arrays.stream(br.readLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondSeq = Arrays.stream(br.readLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        int totalSum = 0;
        while (firstSeq.size() > 0 && secondSeq.size() > 0) {
            Integer firstNum = firstSeq.peek();
            Integer lastNum = secondSeq.pollLast();

            if ((firstNum + lastNum) % 2 == 0) {
                totalSum += firstNum + lastNum;
                firstSeq.poll();
            } else {
                firstSeq.offer(lastNum);
            }
        }

        if (firstSeq.size() == 0) {
            System.out.println("First lootbox is empty");
        } else if (secondSeq.size() == 0) {
            System.out.println("Second lootbox is empty");
        }

        BiConsumer<String, Integer> print = (String message, Integer sum) -> System.out.println(String.format(message, sum));
        Predicate<Integer> checkForEpic = (sum) -> sum >= 100;
        if (checkForEpic.test(totalSum)) {
            printResult(print, "Your loot was epic! Value: %d", totalSum);
        } else {
            printResult(print, "Your loot was poor... Value: %d", totalSum);
        }
    }

    private static void printResult(BiConsumer<String, Integer> func, String message, Integer totalSum) {
        func.accept(message, totalSum);
    }
}
