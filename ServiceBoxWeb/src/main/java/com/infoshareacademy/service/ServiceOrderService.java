package com.infoshareacademy.service;

import com.infoshareacademy.dao.serviceorder.ServiceOrderDao;
import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {
    private final ServiceOrderDao serviceOrderDao;


    public List<ServiceOrderDto> findAll() {
        return serviceOrderDao.findAll().stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }

    public ServiceOrderDto findServiceOrder(Long id) {
        ServiceOrder serviceOrder = serviceOrderDao.findById(id);
        return ServiceOrderDto.fromServiceOrder(serviceOrder);
    }

    public List<ServiceOrderDto> findByQuery(String query) {
        return serviceOrderDao.findByQuery(query).stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }


//    @Transactional
//    public void addServiceOrder(ServiceOrderDto serviceOrderDto) {
//        ServiceOrder serviceOrder = serviceOrderMapper.(serviceOrderDto);
//        serviceOrderDao.save(serviceOrder);
//    }


    public long countByState(ServiceOrderState state) {
        // TODO
        return 0;
    }

    public Optional<ServiceOrder> getLastOrder() {
        // TODO
        return Optional.empty();
    }

    public String generateOrderNumber() {
        Long nextId = serviceOrderDao.countServiceOrders() + 1;
        return nextId +
                "/" +
                LocalDateTime.now().getMonth().getValue() +
                "/" +
                LocalDateTime.now().getYear();
    }
}
