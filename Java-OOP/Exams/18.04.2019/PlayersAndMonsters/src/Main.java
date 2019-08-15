import core.ManagerControllerImpl;
import core.interfaces.ManagerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        ManagerController managerController = new ManagerControllerImpl();
        String input;
        while (!(input = bufferedReader.readLine()).equals("Exit")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            try {
                switch (command) {
                    case "AddPlayer":
                        System.out.println(managerController.addPlayer(tokens[1], tokens[2]));
                        break;
                    case "AddCard":
                        System.out.println(managerController.addCard(tokens[1], tokens[2]));
                        break;
                    case "AddPlayerCard":
                        System.out.println(managerController.addPlayerCard(tokens[1], tokens[2]));
                        break;
                    case "Fight":
                        System.out.println(managerController.fight(tokens[1], tokens[2]));
                        break;
                    case "Report":
                        System.out.println(managerController.report());
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
