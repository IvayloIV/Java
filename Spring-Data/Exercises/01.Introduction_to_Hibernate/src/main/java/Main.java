import commands.Command;
import commands.*;

public class Main {

    public static void main(String[] args) {
        Command command = new RemoveTowns();
        Engine engine = new Engine(command);
        engine.run();
    }
}
