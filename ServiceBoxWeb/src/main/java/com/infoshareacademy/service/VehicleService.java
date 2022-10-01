package com.infoshareacademy.service;


import com.infoshareacademy.dao.vehicle.VehicleDao;
import com.infoshareacademy.dto.client.ClientDto;
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
                .map(vehicleMapper::toDto)
                .toList();
    }

    @Transactional
    public void addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleMapper.fromDto(vehicleDto);
        vehicleDao.save(vehicle);
    }

    public List<VehicleDto> findByQuery(String query) {
        return vehicleDao.findByQuery(query).stream()
                .map(vehicleMapper::toDto)
                .toList();
    }

    public boolean plateNumberExists(String plateNumber) {
        return vehicleDao.findByPlateNumber(plateNumber).isPresent();
    }

    public Vehicle findVehicle(Long id) {
        return vehicleDao.find(id);
    }
}
