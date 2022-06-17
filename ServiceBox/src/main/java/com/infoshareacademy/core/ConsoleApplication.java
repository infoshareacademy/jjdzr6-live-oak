package com.infoshareacademy.core;

import com.infoshareacademy.menu.MainMenu;

public class ConsoleApplication {
    public void start() {
        System.out.println("LiveOak Team!" + System.lineSeparator());

        MainMenu menu = new MainMenu();
        menu.start();
    }
}
