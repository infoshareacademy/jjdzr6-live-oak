package com.infoshareacademy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.entity.ServiceOrder;

import java.util.ArrayList;

public class ServiceOrderRepository extends Repository<ServiceOrder> {
    public ServiceOrderRepository(String filename, TypeReference<ArrayList<ServiceOrder>> typeReference) {
        super(filename, typeReference);
    }
}