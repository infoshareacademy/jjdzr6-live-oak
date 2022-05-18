package com.infoshareacademy.core;

import com.infoshareacademy.security.Security;
import com.infoshareacademy.security.Session;

public class ConsoleApplication {
    public void start() {
        System.out.println("LiveOak Team!");

        Security security = new Security();
        security.login();

        System.out.println("Zalogowano jako: " + Session.getUser().getUsername());
    }
}
