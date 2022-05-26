package com.infoshareacademy.core;

import com.infoshareacademy.menu.Menu;
import com.infoshareacademy.model.Task;

public class ConsoleApplication {
    public void start() {
        System.out.println("LiveOak Team!" + System.lineSeparator());

        // create instance of Menu class
        Menu menu = new Menu();
        int option = 0;
        do {
            option = menu.start();
        } while (option != 4);

        // test

        // show all from memory
        for (Task task : MemoryDB.getTasks()) {
            System.out.println(task);
        }
    }
}
