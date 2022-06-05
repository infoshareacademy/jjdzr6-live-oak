package com.infoshareacademy.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenu implements Menu{
    ArrayList<String> options = new ArrayList<>();
    @Override
    public void showMainMenu(List<String> options) {

        for (String option : options) {
            System.out.println(option);

        }

    }
}
