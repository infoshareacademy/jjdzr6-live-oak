package com.infoshareacademy.repository;

import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.model.Client;

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

        throw new Exception("Nie znaleziono Klienta o podanym numerze (" + id + ")");
    }

    public ArrayList<Client> findAll() {
        return db.getClients();
    }

    public int getNextId() {
        return db.getClients().size() + 1;
    }
}

