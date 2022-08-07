package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.service.ClientService;

import java.util.List;

public class ClientMenu extends AbstractMenu {
    public ClientMenu() {
        options.add(ConsoleOutput.ROW_SEPARATOR);
        options.add("Baza Klientow");
        options.add(ConsoleOutput.ROW_SEPARATOR);
        options.add("1. Dodaj nowego Klienta");
        options.add("2. Wyswietl wszystkich Klientow");
        options.add("3. Wyszukaj/Zaktualizuj dane Klientow");
        options.add("0. Wroc do menu glownego");
        options.add(ConsoleOutput.ROW_SEPARATOR);
        }

        @Override
        public void start() {

            int option = 0;
            do {
                showMainMenu(options);
                option = ConsoleInput.getIntFromRange("Wybierz jedna z opcji (0-3): ", 0, 3);
                ClientService clientService = new ClientService();

                switch (option) {
                    case 1:
                        clientService.createClient("");
                        break;

                    case 2:
                        clientService.showAllClients();
                        break;

                    case 3:
                        clientService.findAndUpdateClient();
                        break;

                    default:
                }

            } while (option != EXIT_OPTION);
        }
}
