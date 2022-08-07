package com.infoshareacademy.repository;

import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.model.Client;
import com.infoshareacademy.model.Vehicle;

import java.util.ArrayList;

public class ClientRepository {
    private final DatabaseInterface db;

    public ClientRepository(DatabaseInterface db) {
        this.db = db;
    }

    public Client findById(int id) throws Exception {
        for (Client client : db.getClients()) {
            if (id == client.getId()) {
                return client;
            }
        }

        throw new Exception("Nie znaleziono pojazdu o podanym numerze (" + id + ")");
    }

    public Client findBySurname(String surname) throws Exception {
        for (Client client : db.getClients()) {
            if (surname.equals(client.getSurname())) {
                return client;
            }
        }

        throw new Exception("Nie znaleziono klienta o nazwisku (" + surname + ")");
    }

    public ArrayList<Client> findAll() {
        return db.getClients();
    }

    public int getNextId() {
        return db.getClients().size() + 1;
    }
}

