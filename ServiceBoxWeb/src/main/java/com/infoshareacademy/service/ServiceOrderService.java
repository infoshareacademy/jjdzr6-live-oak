package com.infoshareacademy.service;

import com.infoshareacademy.entity.ServiceOrder;
import com.infoshareacademy.entity.ServiceOrderState;
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

    public ServiceOrder findServiceOrder(int id){
        return  serviceOrderRepository.find(id);
    }

    public List<ServiceOrder> findByQuery(String query) {
        return serviceOrderRepository.findBy(query);
    }

    public long countByState(ServiceOrderState state) {
        return serviceOrderRepository.findAll()
                .stream()
                .filter(order -> order.getState().equals(state))
                .count();
    }
}
