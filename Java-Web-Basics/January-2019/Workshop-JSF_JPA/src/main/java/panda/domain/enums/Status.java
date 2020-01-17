package panda.domain.enums;

public enum Status {
    Pending, Shipped, Delivered, Acquired;

    private static Status[] vals = values();

    public Status next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
