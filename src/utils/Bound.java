package utils;

import java.awt.*;

public class Bound{
    public int x;
    public int y;
    public int width;
    public int height;

    public Bound(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Bound(int width, int height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
    }

    public Bound(Rectangle rectangle) {
        this.x = 0;
        this.y = 0;
        this.width = rectangle.width;
        this.height = rectangle.height;
    }
}