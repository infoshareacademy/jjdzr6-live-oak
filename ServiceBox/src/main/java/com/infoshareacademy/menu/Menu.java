package com.infoshareacademy.menu;

import java.util.ArrayList;
import java.util.List;

public interface Menu {
    int EXIT_OPTION = 0;
    void start();
    void showMainMenu(List<String> options);

}
