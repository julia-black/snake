package com.ssu.ChernousovaYA.snake.model;

import com.ssu.ChernousovaYA.snake.view.GUI;
import com.ssu.ChernousovaYA.snake.view.Square;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Juli on 23.02.2017.
 */
public class Snake{
    final static int SIZE_SQARE = 20;
    final static int SIZE_BODY = SIZE_SQARE/3*2;

    private static int length;
    private static Direction direction;
    private static int borderTop;
    private static int borderBot;
    private static int borderRight;
    private static int borderLeft;

    public enum Direction {
        UP, DOWN, RIGHT, LEFT
    }

    private static ArrayList<Square> body;

    public Snake(int startLength,  int borderBot, int borderRight) {
        direction = Direction.RIGHT;
        this.length = startLength;
        this.body = new ArrayList<>(startLength);

        this.borderBot = borderBot;
        this.borderLeft = 0;
        this.borderRight = borderRight;
        this.borderTop = 0;

        for (int i = 0; i < length; i++) {
            body.add(new Square(length - i, 0, SIZE_BODY, Color.yellow, SIZE_BODY));
        }
    }
    public void paint(Graphics g){
        for (int i = 0; i < length; i++) {
            body.get(i).paint(g);
        }
    }

    public static Direction getDirection() {
        return direction;
    }

    public static void setDirection(Direction direction) {
        Snake.direction = direction;
    }

    private static boolean isFrog(){
        return body.get(0).getX() == GUI.getFrog().getSquare().getX() && body.get(0).getY() == GUI.getFrog().getSquare().getY();
    }

    private static boolean isSuicide(int headX, int headY){
        for (int i = 1; i <length; i++) {
            if(headX == body.get(i).getX() && headY == body.get(i).getY())
                return true;
        }
        return false;
    }

    public static boolean movingOfDirect(Direction direction){ //true - если не врезался в стену
        if(isFrog()) {
            GUI.getFrog().dead();
            length++;
            body.add(new Square(body.get(length - 2).getX(), body.get(length - 2).getY(), SIZE_BODY, Color.yellow, SIZE_BODY));
        }
        else if(isSuicide(body.get(0).getX(), body.get(0).getY())){
            return false;
        }
        else {
            if (direction == Direction.DOWN) {
                if (body.get(0).getY() >= borderBot * 2 / SIZE_SQARE - 8)
                    return false;
                moveOfDown();
            } else if (direction == Direction.UP) {
                if (body.get(0).getY() <= borderTop )
                    return false;
                moveOfTop();
            } else if (direction == Direction.LEFT) {
                if (body.get(0).getX() <= borderLeft )
                    return false;
                moveOfLeft();
            } else if (direction == Direction.RIGHT) {
                if (body.get(0).getX() > borderRight * 2 / SIZE_SQARE - 10)
                    return false;
                moveOfRight();
            }
        }
        return true;
    }

   private static void movingBody() {
       ArrayList<Square> predBody = new ArrayList<>(body);
       for (int i = 1; i < length; i++) {
           body.set(i, new Square(predBody.get(i - 1).getX(), predBody.get(i - 1).getY(), SIZE_BODY, Color.yellow, SIZE_BODY));
       }
   }

   private static void moveOfRight() { //Движение вправо на одну клетку
       movingBody();
       body.set(0, new Square((body.get(0).getX() + 1) * 1, body.get(0).getY() * 1, SIZE_BODY, Color.yellow, SIZE_BODY));
   }

   private static void moveOfDown() {
       movingBody();
       body.set(0, new Square(body.get(0).getX(), body.get(0).getY() + 1, SIZE_BODY, Color.yellow,SIZE_BODY));
   }

   private static void moveOfLeft() {
       movingBody();
       body.set(0, new Square(body.get(0).getX() - 1, body.get(0).getY(), SIZE_BODY, Color.yellow, SIZE_BODY));
   }
   private static void moveOfTop(){
       movingBody();
       body.set(0, new Square(body.get(0).getX(), body.get(0).getY() - 1, SIZE_BODY, Color.yellow, SIZE_BODY));
   }

   public static int getLength() {
       return length;
   }

   public void setLength(int length) {
       this.length = length;
   }


}