import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ClubParty {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Deque<Hall> halls = new ArrayDeque<>();
        int maxCapacity = Integer.parseInt(bufferedReader.readLine());
        String[] tokens = bufferedReader.readLine().split("\\s+");
        for (int i = tokens.length - 1; i >= 0; i--) {
            String element = tokens[i];
            if (element.matches("^[0-9]+$")) {
                int capacity = Integer.parseInt(element);
                while (!halls.isEmpty()) {
                    Hall topHall = halls.peek();

                    if (!topHall.addReservation(capacity)) {
                        halls.pop();
                        System.out.println(topHall);
                    } else {
                        break;
                    }
                }
            } else {
                halls.offer(new Hall(element.charAt(0), maxCapacity));
            }
        }
    }
}

class Hall {
    private char name;
    private List<Integer> reservation;
    private int maxCapacity;
    private int capacity;

    public Hall(char name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.reservation = new ArrayList<>();
        this.capacity = 0;
    }

    public boolean addReservation(int capacity) {
        if (this.capacity + capacity > this.maxCapacity) {
            return false;
        }

        this.reservation.add(capacity);
        this.capacity += capacity;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%c -> %s",
                this.name,
                this.reservation.stream()
                        .map(a -> a.toString())
                        .collect(Collectors.joining(", ")));
    }
}
