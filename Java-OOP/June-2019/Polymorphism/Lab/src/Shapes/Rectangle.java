package Shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    @Override
    public void calculatePerimeter() {
        this.setPerimeter(2 * this.width + 2 * this.height);
    }

    @Override
    public void calculateArea() {
        this.setArea(this.height * this.width);
    }
}
