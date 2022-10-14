package com.infoshareacademy.service;

import com.infoshareacademy.dao.client.ClientDao;
import com.infoshareacademy.dao.serviceorder.ServiceOrderDao;
import com.infoshareacademy.dto.serviceorder.ServiceOrderDetailsDto;
import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.serviceorder.Note;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {
    private final ServiceOrderDao serviceOrderDao;
    private final ClientDao clientDao;

    public List<ServiceOrderDto> findAll() {
        return serviceOrderDao.findAll().stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }

    public ServiceOrderDto findServiceOrder(Long id) {
        ServiceOrder serviceOrder = serviceOrderDao.findById(id);
        return ServiceOrderDto.fromServiceOrder(serviceOrder);
    }

    public ServiceOrderDetailsDto getServiceOrderDetails(Long id) {
        ServiceOrder serviceOrder = serviceOrderDao.findById(id);
        return ServiceOrderDetailsDto.fromServiceOrder(serviceOrder);
    }

    public List<ServiceOrderDto> findByQuery(String query) {
        return serviceOrderDao.findByQuery(query).stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }

    public boolean isOrderExists(String orderNumber) {
        return serviceOrderDao.findByOrderNumber(orderNumber).isPresent();
    }

    public Long countByState(ServiceOrderState state) {
        return serviceOrderDao.countServiceOrderWithState(state);
    }

    public List<ServiceOrderDto> getLastOrders(int limit) {
        return serviceOrderDao.getLastServiceOrders(limit).stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }

    public String generateOrderNumber() {
        Long nextId = serviceOrderDao.countServiceOrders() + 1;
        return nextId +
                "/" +
                LocalDateTime.now().getMonth().getValue() +
                "/" +
                LocalDateTime.now().getYear();
    }

    @Transactional
    public void addNote(Long orderId, String note) {
        ServiceOrder serviceOrder = serviceOrderDao.findById(orderId);

        Note newNote = new Note(note);
        serviceOrder.getNotes().add(newNote);

        serviceOrderDao.update(serviceOrder);
    }
    @Transactional
    public void updateStatus (Long orderId) {
        ServiceOrder serviceOrder = serviceOrderDao.findById(orderId);
        if(ServiceOrderState.FINISHED.equals(serviceOrder.getState())){
            return;
        }
        if (ServiceOrderState.CREATED.equals(serviceOrder.getState())){
            serviceOrder.setState(ServiceOrderState.IN_PROGRESS);
        }else {
            serviceOrder.setState(ServiceOrderState.FINISHED);
        }
        serviceOrderDao.update(serviceOrder);
    }

    public List<ServiceOrderDto> filterByState(ServiceOrderState state) {
        return serviceOrderDao.filterByState(state).stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }

    public List<ServiceOrderDto> findByCriteria(String searchQuery, String filter) {
        List<ServiceOrderDto> serviceOrders = null;

        if (searchQuery.isBlank()) {
            serviceOrders = findAll();
        } else {
            serviceOrders = findByQuery(searchQuery);
        }

        Map<String, ServiceOrderState> filters = Map.of(
                "created", ServiceOrderState.CREATED,
                "in-progress", ServiceOrderState.IN_PROGRESS,
                "finished", ServiceOrderState.FINISHED
        );

        if (!filter.isBlank() && filters.containsKey(filter)) {
            serviceOrders = serviceOrders.stream()
                    .filter(serviceOrderDto -> serviceOrderDto.getState().equals(filters.get(filter)))
                    .toList();
        }

        return serviceOrders;
    }


    public List<ServiceOrderDto> findServiceOrdersByClientEmail(String email) {
        Optional<Client> client = clientDao.findByEmail(email);
        List<ServiceOrder> serviceOrders = new ArrayList<>();

        if (client.isPresent()) {
            List<Vehicle> vehicles = client.get().getVehicles();

            for (Vehicle vehicle : vehicles) {
                serviceOrders.addAll(vehicle.getServiceOrders());
            }

            return serviceOrders.stream()
                    .map(ServiceOrderDto::fromServiceOrder)
                    .toList();
        }

        return new ArrayList<>();
    }

}
