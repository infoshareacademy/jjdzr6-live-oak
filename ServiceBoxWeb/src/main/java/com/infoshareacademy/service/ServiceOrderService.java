package com.infoshareacademy.service;

import com.infoshareacademy.dto.serviceorder.ServiceOrderDetailsDto;
import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.serviceorder.Note;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.repository.ClientRepository;
import com.infoshareacademy.repository.ServiceOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;

    private final ClientRepository clientRepository;

    public List<ServiceOrderDto> findAll() {
        return serviceOrderRepository.findAll().stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }

    public ServiceOrderDto findServiceOrder(Long id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(id).get();
        return ServiceOrderDto.fromServiceOrder(serviceOrder);
    }

    public ServiceOrderDetailsDto getServiceOrderDetails(Long id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(id).get();
        return ServiceOrderDetailsDto.fromServiceOrder(serviceOrder);
    }


    public Page<ServiceOrderDto> findByQuery(String query, Pageable pageable) {
        Page<ServiceOrder> page = serviceOrderRepository.findByQuery(query, pageable);

        List<ServiceOrderDto> servicesOnPage = page.stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();

        return new PageImpl<>(servicesOnPage, pageable, page.getTotalElements());
    }


    public boolean isOrderExists(String orderNumber) {
        return serviceOrderRepository.findByOrderNumber(orderNumber).isPresent();
    }

    public Long countByState(ServiceOrderState state) {
        return serviceOrderRepository.countServiceOrderWithState(state);
    }

    public List<ServiceOrderDto> getLastOrders(int limit) {
        return serviceOrderRepository.getLastServiceOrders(limit).stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();
    }

    public String generateOrderNumber() {
        Long nextId = serviceOrderRepository.count() + 1;
        return nextId +
                "/" +
                LocalDateTime.now().getMonth().getValue() +
                "/" +
                LocalDateTime.now().getYear();
    }

    @Transactional
    public void addNote(Long orderId, String note) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId).get();

        Note newNote = new Note(note);
        serviceOrder.getNotes().add(newNote);

        serviceOrderRepository.save(serviceOrder);
    }

    @Transactional
    public void updateStatus(Long orderId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId).get();
        if (ServiceOrderState.FINISHED.equals(serviceOrder.getState())) {
            return;
        }
        if (ServiceOrderState.CREATED.equals(serviceOrder.getState())) {
            serviceOrder.setState(ServiceOrderState.IN_PROGRESS);
        } else {
            serviceOrder.setState(ServiceOrderState.FINISHED);
        }
        serviceOrderRepository.save(serviceOrder);
    }


    public Page<ServiceOrderDto> findByState(ServiceOrderState state, Pageable pageable) {

        Page<ServiceOrder> page = serviceOrderRepository.findServiceOrderByState(state, pageable);
        List<ServiceOrderDto> serviceOnPage = page.stream()
                .map(ServiceOrderDto::fromServiceOrder)
                .toList();

        return new PageImpl<>(serviceOnPage, pageable, page.getTotalElements());
    }


    public List<ServiceOrderDto> findServiceOrdersByClientEmail(String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
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

