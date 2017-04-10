package com.ssu.ChernousovaYA.snake.main;

/**
 * Created by ChernousovaYA on 17.02.2017.
 */

import com.ssu.ChernousovaYA.snake.model.Parametrs;
import com.ssu.ChernousovaYA.snake.view.FormSetting;
import com.ssu.ChernousovaYA.snake.view.GUI;

public class Main {
    static public void main(String[] args) {
        Parametrs param = new Parametrs(25,25,3,5,1);
        FormSetting formSetting = new FormSetting(param);
        GUI gui = new GUI(param);
        formSetting.setParrent(gui);
        gui.setChild(formSetting);

    }
}
