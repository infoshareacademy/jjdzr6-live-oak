package com.infoshareacademy.service;


import com.infoshareacademy.dao.vehicle.VehicleDao;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleDao vehicleDao;

    public List<VehicleDto> findAll() {
        List<Vehicle> vehicleList = vehicleDao.findAll();
        return vehicleList.stream()
                .map(VehicleDto::fromVehicle)
                .toList();
    }

    public List<VehicleDto> findByQuery(String query) {
        return vehicleDao.findByQuery(query).stream()
                .map(VehicleDto::fromVehicle)
                .toList();
    }

    public boolean isPlateNumberExists(String plateNumber) {
        return vehicleDao.findByPlateNumber(plateNumber).isPresent();
    }

    public VehicleDto findById(Long id) {
        Vehicle vehicle = vehicleDao.findById(id);
        return VehicleDto.fromVehicle(vehicle);
    }

    public List<VehicleDto> getClientVehicles(Long clientId) {
        List<Vehicle> vehicleList = vehicleDao.findByClientId(clientId);
        return vehicleList.stream()
                .map(VehicleDto::fromVehicle)
                .toList();
    }
}
