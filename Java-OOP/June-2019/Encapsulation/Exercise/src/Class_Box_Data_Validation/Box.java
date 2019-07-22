package Class_Box_Data_Validation;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        checkNegativeValue("Length", length);
        this.length = length;
    }

    private void setWidth(double width) {
        checkNegativeValue("Width", width);
        this.width = width;
    }

    private void setHeight(double height) {
        checkNegativeValue("Height", height);
        this.height = height;
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateSurfaceArea() {
        return 2 * this.length * this.width + this.calculateLateralSurfaceArea();
    }

    private void checkNegativeValue(String name, double size) {
        if (size <= 0) {
            throw new IllegalArgumentException(name + " cannot be zero or negative.");
        }
    }
}
