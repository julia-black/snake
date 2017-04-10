package com.ssu.ChernousovaYA.snake.model;

import com.ssu.ChernousovaYA.snake.view.Square;

import java.awt.*;

/**
 * Created by Juli on 10.03.2017.
 */
public interface Frog {
    void paint(Graphics g);
    void dead();
    boolean isEaten();
    void createNewFrog();
    Square getSquare();
}