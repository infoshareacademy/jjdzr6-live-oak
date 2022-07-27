package com.infoshareacademy.service.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.entity.Client;
import com.infoshareacademy.entity.RepairCard;
import com.infoshareacademy.entity.ServiceOrder;
import com.infoshareacademy.entity.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class JsonFileHandlerConfiguration {
    @Value("${db.json.vehicle:vehicle.json}")
    private String vehicleJsonFile;

    @Value("${db.json.client:client.json}")
    private String clientJsonFile;

    @Value("${db.json.service-order:service-order.json}")
    private String orderJsonFile;

    @Value("${db.json.repair-card:repair-card.json}")
    private String repairCardJsonFile;

    @Bean
    public VehicleJsonFileHandler vehicleJsonFileHandler() {
        TypeReference ref = new TypeReference<ArrayList<Vehicle>>() {
        };
        return new VehicleJsonFileHandler(vehicleJsonFile, ref);
    }

    @Bean
    public ClientJsonFileHandler clientJsonFileHandler() {
        TypeReference ref = new TypeReference<ArrayList<Client>>() {
        };
        return new ClientJsonFileHandler(clientJsonFile, ref);
    }

    @Bean
    public ServiceOrderJsonFileHandler serviceOrderJsonFileHandler() {
        TypeReference ref = new TypeReference<ArrayList<ServiceOrder>>() {
        };
        return new ServiceOrderJsonFileHandler(orderJsonFile, ref);
    }

    @Bean
    public RepairCardJsonFileHandler repairCardJsonFileHandler() {
        TypeReference ref = new TypeReference<ArrayList<RepairCard>>() {
        };
        return new RepairCardJsonFileHandler(repairCardJsonFile, ref);
    }
}
