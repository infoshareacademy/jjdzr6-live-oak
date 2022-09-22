package com.infoshareacademy.service;


import com.infoshareacademy.dao.vehicle.VehicleDao;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleDao vehicleDao;


    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    public Vehicle findVehicleById(long id) {
        return vehicleDao.find(id);
    }


    public List<Vehicle> getClientVehicles(Client client) {
        return new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleDao.save(vehicle);
    }

    public List<Vehicle> findByQuery(String query) {
        return new ArrayList<>();
    }
}
