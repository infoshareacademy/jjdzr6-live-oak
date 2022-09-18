package com.infoshareacademy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.entity.client.Client;
import java.util.List;

import java.util.ArrayList;

public class ClientRepository extends Repository<Client> {
    public ClientRepository(String filename, TypeReference<ArrayList<Client>> typeReference) {
        super(filename, typeReference);
    }

    public List<Client> findBy(String search) {
        return objects.stream()
                .filter(e -> e.toString().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}