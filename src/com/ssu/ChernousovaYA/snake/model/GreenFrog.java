package com.ssu.ChernousovaYA.snake.model;

import com.ssu.ChernousovaYA.snake.view.Square;
import java.awt.*;
import java.util.Random;

/**
 * Created by Juli on 10.03.2017.
 */

public class GreenFrog implements Frog {

    private final int SIZE_FROG = Snake.SIZE_SQARE/2;
    private Square square;
    private int borderRight;
    private int borderBot;
    private static boolean dead;

    public GreenFrog(int borderRight, int borderBot) {
        this.borderRight = borderRight;
        this.borderBot = borderBot;
        dead = false;
        square = new Square(new Random().nextInt(40) + 1, new Random().nextInt(37), SIZE_FROG , Color.GREEN, Snake.SIZE_SQARE/3*2);
    }

    public void dead() {
        dead = true;
    }

    @Override
    public boolean isEaten() {
        return dead;
    }

    public void paint(Graphics g) {
        square.paint(g);
    }

    public void createNewFrog() {
       int x,y;
       x = new Random().nextInt(40) + 1;
       y = new Random().nextInt(37) + 0;
       square.setCoordinat(x,y);
        dead = false;
    }

    @Override
    public Square getSquare() {
        return square;
    }
}
