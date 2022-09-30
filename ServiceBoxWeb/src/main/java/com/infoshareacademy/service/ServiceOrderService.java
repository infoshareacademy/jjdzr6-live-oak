package com.infoshareacademy.service;

import com.infoshareacademy.dao.serviceorder.ServiceOrderDao;
import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.mappers.serviceorder.ServiceOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {

    private final ServiceOrderDao serviceOrderDao;
    private final ServiceOrderMapper serviceOrderMapper;

    public List<ServiceOrderDto> findAll() {
        return serviceOrderDao.findAll().stream()
                .map(serviceOrderMapper::toDto)
                .toList();
    }

    @Transactional
    public void addServiceOrder(ServiceOrderDto serviceOrderDto) {
        ServiceOrder serviceOrder = serviceOrderMapper.fromDto(serviceOrderDto);
        serviceOrderDao.save(serviceOrder);
    }

    public ServiceOrder findServiceOrder(long id) {
        return serviceOrderDao.find(id);
    }

    public List<ServiceOrderDto> findByQuery(String query) {
        return serviceOrderDao.findByQuery(query).stream()
                .map(serviceOrderMapper::toDto)
                .toList();
    }

    public long countByState(ServiceOrderState state) {
        return 0;
    }

    public Optional<ServiceOrder> getLastOrder() {
        return Optional.empty();
    }
}
