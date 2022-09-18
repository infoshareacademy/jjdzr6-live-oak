package com.infoshareacademy.service;

import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        try {
            serviceOrderRepository.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public Optional<ServiceOrder> getLastOrder() {
        return serviceOrderRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(ServiceOrder::getCreatedAt).reversed())
                .findFirst();
    }
}
