package solidLab.p03_LiskovSubstitution.p01_Square;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return this.side;
    }

    @Override
    public double getArea() {
        return this.side * this.side;
    }
}
