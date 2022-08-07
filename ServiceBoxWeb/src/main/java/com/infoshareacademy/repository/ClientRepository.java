package com.infoshareacademy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.entity.Client;

import java.util.ArrayList;

public class ClientRepository extends Repository<Client> {
    public ClientRepository(String filename, TypeReference<ArrayList<Client>> typeReference) {
        super(filename, typeReference);
    }
}