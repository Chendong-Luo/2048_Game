package model;

public class Position {
    private int xPosition;
    private int yPosition;

    public Position(int x, int y) {
        xPosition = x;
        yPosition = y;
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }
}
