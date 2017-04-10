package com.ssu.ChernousovaYA.snake.controller;

import com.ssu.ChernousovaYA.snake.model.Snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Juli on 10.03.2017.
 */
public class SnakeMouseListener implements MouseListener {
    private static Snake snake;

    public SnakeMouseListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()== MouseEvent.BUTTON1)
        //Левая кнопка мыши
        {
           if(snake.getDirection() == Snake.Direction.RIGHT){
               snake.setDirection(Snake.Direction.UP);
           }
           else if (snake.getDirection() == Snake.Direction.UP){
               snake.setDirection(Snake.Direction.LEFT);
           }
           else if (snake.getDirection() == Snake.Direction.LEFT){
               snake.setDirection(Snake.Direction.DOWN);
           }
           else if (snake.getDirection() == Snake.Direction.DOWN){
               snake.setDirection(Snake.Direction.RIGHT);
           }
        }
        //Правая кнопка мыши
        else if (e.getButton()== MouseEvent.BUTTON3) {
            if(snake.getDirection() == Snake.Direction.RIGHT){
                snake.setDirection(Snake.Direction.DOWN);
            }
            else if (snake.getDirection() == Snake.Direction.UP){
                snake.setDirection(Snake.Direction.RIGHT);
            }
            else if (snake.getDirection() == Snake.Direction.LEFT){
                snake.setDirection(Snake.Direction.UP);
            }
            else if (snake.getDirection() == Snake.Direction.DOWN){
                snake.setDirection(Snake.Direction.LEFT);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
