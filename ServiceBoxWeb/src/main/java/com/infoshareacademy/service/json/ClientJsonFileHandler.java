package com.infoshareacademy.service.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.core.JsonFileHandler;
import com.infoshareacademy.entity.Client;

import java.util.ArrayList;

public class ClientJsonFileHandler extends JsonFileHandler<Client> {
    public ClientJsonFileHandler(String filename, TypeReference<ArrayList<Client>> typeReference) {
        super(filename, typeReference);
    }
}