package com.ssu.ChernousovaYA.snake.view;

import com.ssu.ChernousovaYA.snake.controller.SnakeMouseListener;
import com.ssu.ChernousovaYA.snake.model.Frog;
import com.ssu.ChernousovaYA.snake.model.GreenFrog;
import com.ssu.ChernousovaYA.snake.model.Parametrs;
import com.ssu.ChernousovaYA.snake.model.Snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Juli on 23.02.2017.
 */

public class GUI extends JFrame {
    private final int SIZE_BUTT_PANEL = 40;
    private static JButton startButton;
    private static JButton stopButton;
    private static JButton pauseButton;
    private static JPanel gameWindow;

    private JPanel rootPanel;
    private JPanel buttPanel;
    private JButton settingButton;
    private JFrame child;
    private JLabel counter;

    private static Snake snake;
    private static Frog frog;

    private JLabel counterScore;
    private int count;
    private int speed;
    private Snake.Direction direction;

    private boolean suicide;
    private boolean startGame;

    @Override
    public void paint(Graphics g) {
       super.paint(g);

        snake.paint(gameWindow.getGraphics());
        frog.paint(gameWindow.getGraphics());

        if(suicide) {
            g.setColor(Color.cyan);
            g.drawString("You lose", 200, 200);
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            pauseButton.setEnabled(false);
        }
        new Square(-1,-1,0,Color.black, 0).paint(gameWindow.getGraphics());
        counterScore.setText(" "+ count);
    }

    public GUI(Parametrs param){
        initializeComponent(param);
        setVisible(true);

        frog = new GreenFrog(gameWindow.getHeight(),gameWindow.getWidth());
        snake = new Snake(param.getLength(), gameWindow.getHeight(),gameWindow.getWidth());
        suicide = false;
        count = 0;
        speed = 200 / param.getSpeed();

        this.addMouseListener(new SnakeMouseListener(snake));
        startGame = false;

        settingButton.addActionListener(
                e -> child.setVisible(true)
        );

        startButton.addActionListener(e -> {
            startGame = true;
            snake = new Snake(param.getLength(), gameWindow.getHeight(),gameWindow.getWidth());
            snake.setDirection(Snake.Direction.RIGHT);
            count = 0;
            suicide = false;
            stopButton.setEnabled(true);
            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
            startSnake();
            startFrog();
        });

        pauseButton.addActionListener(e -> {
            if (startGame) {
                startGame = false;
            } else {
                startGame = true;
                startSnake();
                startFrog();
            }
        });

        stopButton.addActionListener(
                e -> {
                    startGame = false;
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);
                    pauseButton.setEnabled(false);
                }
        );
    }

    private void startSnake(){
        Thread thread = new Thread(() -> {
            while (startGame) {
                if(!snake.movingOfDirect(snake.getDirection())){
                    startGame = false;
                    suicide = true;
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
               }
               repaint();
               try {
                   Thread.sleep(speed);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            }
        });
        thread.start();
    }

    private void startFrog() {
        Thread thread = new Thread(() -> {
            while (startGame) {
                if (frog.isEaten()) {
                    count++;
                    frog.createNewFrog();
                }
                repaint();
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static Frog getFrog() {
        return frog;
    }

    private void initializeComponent(Parametrs param){
        setResizable(false);
        rootPanel = new JPanel(new BorderLayout());
        buttPanel = new JPanel();
        setContentPane(rootPanel);

        counter = new JLabel("Score:");
        counterScore = new JLabel(Integer.toString(0));
        stopButton = new JButton("Stop");
        startButton = new JButton("Start");
        settingButton = new JButton("Setting");
        pauseButton = new JButton("Pause");

        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.GREEN);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.GREEN);
        stopButton.setBackground(Color.BLACK);
        stopButton.setForeground(Color.GREEN);
        settingButton.setBackground(Color.BLACK);
        settingButton.setForeground(Color.GREEN);

        stopButton.setEnabled(false);
        startButton.setFocusable(false);
        stopButton.setFocusable(false);
        settingButton.setFocusable(false);
        pauseButton.setFocusable(false);

        setLayout(new BorderLayout());
        gameWindow = new JPanel(new BorderLayout());
        gameWindow.setPreferredSize(new Dimension( param.getN()*20, param.getM()*20));
        gameWindow.setLocation(0,0);
        gameWindow.setBackground(Color.black);
        buttPanel.setPreferredSize(new Dimension(param.getN(),SIZE_BUTT_PANEL));

        buttPanel.setBackground(Color.GREEN);
        rootPanel.add(buttPanel, BorderLayout.NORTH);
        buttPanel.add(startButton);
        buttPanel.add(pauseButton);
        buttPanel.add(stopButton);

        buttPanel.add(settingButton);
        buttPanel.add(counter);
        buttPanel.add(counterScore);
        this.setBounds(400, 400, param.getN() * 20, param.getM() * 20+ SIZE_BUTT_PANEL);
        //20px - одна клетка
        setContentPane(rootPanel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttPanel, BorderLayout.NORTH);
        getContentPane().add(gameWindow, BorderLayout.LINE_END);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setChild(FormSetting child) {
        this.child = child;
    }


}