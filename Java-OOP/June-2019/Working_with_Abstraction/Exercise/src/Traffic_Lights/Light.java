package Traffic_Lights;

public enum Light {
    RED(0),
    GREEN(1),
    YELLOW(2);

    private int value;
    Light(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
