package com.infoshareacademy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    @Value("${db.json.vehicle:vehicle.json}")
    private String vehicleJsonFile;

    @Value("${db.json.client:client.json}")
    private String clientJsonFile;

    @Value("${db.json.service-order:service-order.json}")
    private String orderJsonFile;

    @Bean
    public VehicleRepository vehicleJsonFileHandler() {
        return new VehicleRepository(vehicleJsonFile, new TypeReference<>() {
        });
    }

    @Bean
    public ClientRepository clientJsonFileHandler() {
        return new ClientRepository(clientJsonFile, new TypeReference<>() {
        });
    }

    @Bean
    public ServiceOrderRepository serviceOrderJsonFileHandler() {
        return new ServiceOrderRepository(orderJsonFile, new TypeReference<>() {
        });
    }
}
