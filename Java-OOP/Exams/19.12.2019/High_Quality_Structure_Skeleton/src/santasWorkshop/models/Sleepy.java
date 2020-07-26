package santasWorkshop.models;

public class Sleepy extends BaseDwarf {

    public Sleepy(String name) {
        super(name, 50);
    }

    @Override
    public void work() {
        super.reduceEnergy(5);
        super.work();
    }
}
