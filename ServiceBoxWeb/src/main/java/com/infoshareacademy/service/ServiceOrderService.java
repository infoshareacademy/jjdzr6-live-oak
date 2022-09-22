package com.infoshareacademy.service;

import com.infoshareacademy.dao.serviceorder.ServiceOrderDao;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {

    private final ServiceOrderDao serviceOrderDao;

    public List<ServiceOrder> findAll() {
        return serviceOrderDao.findAll();
    }

    public void addServiceOrder(ServiceOrder serviceOrder) {
        serviceOrderDao.save(serviceOrder);
    }

    public ServiceOrder findServiceOrder(long id) {
        return serviceOrderDao.find(id);
    }

    public List<ServiceOrder> findByQuery(String query) {
        return new ArrayList<>();
    }

    public long countByState(ServiceOrderState state) {
        return 0;
    }

    public Optional<ServiceOrder> getLastOrder() {
        return Optional.empty();
    }
}
