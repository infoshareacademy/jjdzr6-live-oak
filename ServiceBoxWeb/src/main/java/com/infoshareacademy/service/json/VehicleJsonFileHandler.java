package com.infoshareacademy.service.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.core.JsonFileHandler;
import com.infoshareacademy.entity.Vehicle;

import java.util.ArrayList;

public class VehicleJsonFileHandler extends JsonFileHandler<Vehicle> {
    public VehicleJsonFileHandler(String filename, TypeReference<ArrayList<Vehicle>> typeReference) {
        super(filename, typeReference);
    }
}