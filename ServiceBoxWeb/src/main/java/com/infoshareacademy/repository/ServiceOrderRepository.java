package com.infoshareacademy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.entity.ServiceOrder;

import java.util.ArrayList;
import java.util.List;

public class ServiceOrderRepository extends Repository<ServiceOrder> {
    public ServiceOrderRepository(String filename, TypeReference<ArrayList<ServiceOrder>> typeReference) {
        super(filename, typeReference);
    }

    public List<ServiceOrder> findBy(String search) {
        return objects.stream()
                .filter(e -> e.toString().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}