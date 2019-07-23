package Say_Hello;

public interface Person {
    public abstract String getName();

    public default String sayHello() {
        return "Hello";
    }
}
