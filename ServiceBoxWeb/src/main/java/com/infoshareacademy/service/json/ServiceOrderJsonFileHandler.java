package com.infoshareacademy.service.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.core.JsonFileHandler;
import com.infoshareacademy.entity.ServiceOrder;

import java.util.ArrayList;

public class ServiceOrderJsonFileHandler extends JsonFileHandler<ServiceOrder> {
    public ServiceOrderJsonFileHandler(String filename, TypeReference<ArrayList<ServiceOrder>> typeReference) {
        super(filename, typeReference);
    }
}