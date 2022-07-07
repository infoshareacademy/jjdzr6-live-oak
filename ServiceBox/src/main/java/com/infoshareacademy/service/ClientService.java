package com.infoshareacademy.service;

import com.infoshareacademy.core.*;
import com.infoshareacademy.model.Client;
import com.infoshareacademy.repository.ClientRepository;

import java.util.ArrayList;

public class ClientService {

    private final DatabaseInterface database = new JsonFileDatabase();
    private final ClientRepository clientRepository = new ClientRepository(database);
    public Client createClient(String surname) {

        int id = clientRepository.getNextId();

        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Dodawanie nowego Klienta");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        if (surname.isEmpty()) {
            surname= ConsoleInput.getString("Podaj nazwisko Klienta: ");
        }

        String name = ConsoleInput.getString("Wprowadz imie Klienta: ");
        String email = ConsoleInput.getEmail("Wprowadz adres e-mail Klienta ");
        String telephoneNumber = ConsoleInput.getString("Wprowadz nr telefonu Klienta: ");

        Client client = new Client(id, name, surname, email, telephoneNumber);

        database.addClient(client);
        ConsoleOutput.alert("Dodano nowego Klienta");
        ConsoleInput.waitForEnter();

        return client;
    }

    public void showAllClients() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Wszyscy Klienci w bazie");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        ArrayList<Client> myAllClients = clientRepository.findAll();

        if (myAllClients.isEmpty()) {
            ConsoleOutput.alert("Nie dodano jeszcze zadnego Klienta do bazy");
        } else {
            for (Client client : myAllClients) {
                System.out.println(client);
            }
        }

        ConsoleInput.waitForEnter();
    }
    public void editClient(Client client) {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Edycja Klienta o ID " + client.getId());
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        int option = 0;
        do {
            System.out.println("1. Popraw nazwisko: ");
            System.out.println("2. Popraw imie: ");
            System.out.println("3. Popraw adres e-mail: ");
            System.out.println("4. Popraw numer telefonu: ");
            System.out.println("0. Zakoncz");
            System.out.println(ConsoleOutput.ROW_SEPARATOR);
            option = ConsoleInput.getIntFromRange("Wybierz pole do zmiany: ", 0, 4);
            switch (option) {
                case 1:
                    String surname = ConsoleInput.getString("Podaj nowe nazwisko Klienta: ");
                    client.setSurname(surname);
                    break;
                case 2:
                    String name = ConsoleInput.getString("Podaj nowe imie Klienta: ");
                    client.setName(name);
                    break;
                case 3:
                    String email = ConsoleInput.getString("Podaj nowy adres e-mail Klienta: ");
                    client.setEmail(email);
                    break;
                case 4:
                    String telephoneNumber = ConsoleInput.getString("Podaj nowy numer telefonu Klienta: ");
                    client.setTelephoneNumber(telephoneNumber);
                    break;

                default:
                    ConsoleOutput.alert("Zakonczono aktualizacje Klienta o numerze ID" + client.getId());
            }
            ConsoleOutput.alert("Dane zostaly zaktualizowane");
            ConsoleInput.waitForEnter();
        } while (option != 0);
    }
    public void findAndUpdateClient() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Wyszukaj Klienta po ID");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        int id= ConsoleInput.getInt("Podaj numer ID Klienta: ");
        try {
            Client client = clientRepository.findById(id);
            System.out.println(client);
            String letter = ConsoleInput.getString("Czy chcesz zaktualizowac dane Klienta?  Wybierz opcje: [T/N] ");
            if (letter.equalsIgnoreCase("T")) {
                editClient(client);
            }
        } catch (Exception e) {
            ConsoleOutput.alert(e.getMessage());
            ConsoleInput.waitForEnter();
        }
    }

}

