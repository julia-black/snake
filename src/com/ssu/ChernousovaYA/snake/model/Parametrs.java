package com.ssu.ChernousovaYA.snake.model;

/**
 * Created by Juli on 23.02.2017.
 */
public class Parametrs {
    private static int n;
    private static int m;
    private static int length;
    private static int count;
    private static int speed;

    public static int getCount() {
        return count;
    }

    public static int getSpeed() {
        return  speed;
    }

    public static void setSpeed(int speed) {
        Parametrs.speed = speed;
    }

    public static void setCount(int count) {
        Parametrs.count = count;
    }

    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        if(length < 2){
            Exception ex = new Exception("Value of length snake should > 2");
            ex.printStackTrace();
            System.exit(0);
        }
        Parametrs.length = length;
    }

    public static int getN() {
        return n;
    }

    public static int getM() {

        return m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }

    public Parametrs(int n, int m, int length, int count, int speed) {
        setN(n);
        setM(m);
        setLength(length);
        setCount(count);
        setSpeed(speed);
    }
}
