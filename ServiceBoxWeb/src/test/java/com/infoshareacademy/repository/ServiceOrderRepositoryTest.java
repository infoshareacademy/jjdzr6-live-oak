package com.infoshareacademy.repository;

import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.entity.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ServiceOrderRepositoryTest {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        Client client
                = new Client("John Doe", null, "567567567", "test@gmail.com", false);

        Vehicle vehicle
                = new Vehicle("Opel", "Astra", "PK12345", 1.2, 2010, 85000, null);

        vehicle.setClient(client);
        clientRepository.save(client);
        vehicleRepository.save(vehicle);

        ServiceOrder so1 = new ServiceOrder("1/11/2022", "Wymiana opon 1", true, 500);
        ServiceOrder so2 = new ServiceOrder("2/12/2022", "Wymiana opon 2", true, 600);
        ServiceOrder so3 = new ServiceOrder("3/12/2022", "Wymiana opon 3", true, 700);
        ServiceOrder so4 = new ServiceOrder("4/10/2022", "Wymiana opon 4", true, 800);
        ServiceOrder so5 = new ServiceOrder("5/05/2022", "Wymiana opon 5", true, 900);

        so2.setState(ServiceOrderState.IN_PROGRESS);

        so1.setVehicle(vehicle);
        so2.setVehicle(vehicle);
        so3.setVehicle(vehicle);
        so4.setVehicle(vehicle);
        so5.setVehicle(vehicle);

        serviceOrderRepository.saveAll(List.of(so1, so2, so3, so4, so5));
    }

    @Test
    void findByQuery() {
        List<ServiceOrder> orders = serviceOrderRepository.findByQuery("/12", Pageable.unpaged()).getContent();

        assertThat(orders)
                .hasSize(2)
                .map(ServiceOrder::getOrderNumber)
                .containsOnly("2/12/2022", "3/12/2022");
    }

    @Test
    void findByOrderNumber() {
        Optional<ServiceOrder> serviceOder = serviceOrderRepository.findByOrderNumber("4/10/2022");

        assertThat(serviceOder)
                .isPresent()
                .get()
                .returns("4/10/2022", ServiceOrder::getOrderNumber)
                .returns(800, ServiceOrder::getMaxCost);
    }

    @Test
    void countByState() {
        Long created = serviceOrderRepository.countByState(ServiceOrderState.CREATED);
        Long inProgress = serviceOrderRepository.countByState(ServiceOrderState.IN_PROGRESS);

        assertEquals(4, created);
        assertEquals(1, inProgress);
    }

    @Test
    void findFirst3ByOrderByIdDesc() {
        List<ServiceOrder> first3 = serviceOrderRepository.findFirst3ByOrderByIdDesc();

        assertThat(first3)
                .hasSize(3)
                .map(ServiceOrder::getOrderNumber)
                .containsOnly("3/12/2022", "4/10/2022", "5/05/2022");
    }

    @Test
    void findServiceOrderByState() {
        List<ServiceOrder> orders = serviceOrderRepository.findServiceOrderByState(ServiceOrderState.IN_PROGRESS, Pageable.unpaged()).getContent();

        assertThat(orders)
                .hasSize(1)
                .first()
                .returns("2/12/2022", ServiceOrder::getOrderNumber);
    }
}