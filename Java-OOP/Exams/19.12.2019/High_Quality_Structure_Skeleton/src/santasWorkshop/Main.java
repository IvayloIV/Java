package santasWorkshop;

import santasWorkshop.core.Engine;
import santasWorkshop.core.EngineImpl;

class Main {
    public static void main(String []args) {
        Engine eg = new EngineImpl();
        eg.run();
    }
}