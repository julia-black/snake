package com.ssu.ChernousovaYA.snake.view;

import java.awt.*;

/**
 * Created by Juli on 05.03.2017.
 */
public class Square {
    private int radius;
    private int x;
    private int y;
    private int size;
    private Color color;

    public Square(int x, int y, int radius, Color color, int size) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.radius = radius;
        this.size = size;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x * size, y * size , radius, radius);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinat(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
