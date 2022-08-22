package com.infoshareacademy.service;

import com.infoshareacademy.entity.ServiceOrder;
import com.infoshareacademy.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOrderService {
    private final ServiceOrderRepository serviceOrderRepository;

    @Autowired
    public ServiceOrderService(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    public List<ServiceOrder> findAll(){
        return serviceOrderRepository.findAll();
    }

    public void addServiceOrder(ServiceOrder serviceOrder){
        serviceOrderRepository.add(serviceOrder);
    }
}
