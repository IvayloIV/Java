package solidLab.p01_SingleResponsibility.p01_DrawingShape;

import solidLab.p01_SingleResponsibility.p01_DrawingShape.interfaces.Shape;

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(2, 1);
        DrawingManagerImpl drawingManager = new DrawingManagerImpl();
        drawingManager.draw(rectangle);
    }
}
