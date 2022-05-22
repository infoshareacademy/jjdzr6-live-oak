package com.infoshareacademy.core;

import com.infoshareacademy.menu.Menu;

public class ConsoleApplication {
    public void start() {
        System.out.println("LiveOak Team!");
        System.out.println(""); // add empty line

        // create instance of Menu class
        Menu menu = new Menu();
        menu.start();
    }
}
