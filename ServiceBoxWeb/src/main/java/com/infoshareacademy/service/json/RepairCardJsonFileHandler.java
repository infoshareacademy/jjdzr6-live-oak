package com.infoshareacademy.service.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.core.JsonFileHandler;
import com.infoshareacademy.entity.RepairCard;

import java.util.ArrayList;

public class RepairCardJsonFileHandler extends JsonFileHandler<RepairCard> {
    public RepairCardJsonFileHandler(String filename, TypeReference<ArrayList<RepairCard>> typeReference) {
        super(filename, typeReference);
    }
}