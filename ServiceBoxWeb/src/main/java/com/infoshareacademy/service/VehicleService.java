package com.infoshareacademy.service;


import com.infoshareacademy.dao.vehicle.VehicleDao;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.mappers.vehicle.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleDao vehicleDao;
    private final VehicleMapper vehicleMapper;

    public List<VehicleDto> findAll() {
        List<Vehicle> vehicleList = vehicleDao.findAll();
        return vehicleList.stream()
                .map(v->vehicleMapper.toDto(v))
                .toList();
    }

    public Vehicle findVehicleById(long id) {
        return vehicleDao.find(id);
    }


    public List<Vehicle> getClientVehicles(Client client) {
        return new ArrayList<>();
    }

    @Transactional
    public void addVehicle(Vehicle vehicle) {
        vehicleDao.save(vehicle);
    }

    public List<Vehicle> findByQuery(String query) {
        return new ArrayList<>();
    }
}
