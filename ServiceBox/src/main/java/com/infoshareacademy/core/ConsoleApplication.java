package com.infoshareacademy.core;

import com.infoshareacademy.menu.Menu;

public class ConsoleApplication {
    public void start() {
        System.out.println("LiveOak Team!");
        System.out.println("");
        Menu menu = new Menu();
        menu.showMenu();
    }
}
