package com.infoshareacademy.service;

import com.infoshareacademy.dto.serviceorder.CreateServiceOrderDto;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.repository.ServiceOrderRepository;
import com.infoshareacademy.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private final ServiceOrderRepository serviceOrderRepository;

    public List<VehicleDto> findAll() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        return vehicleList.stream()
                .map(VehicleDto::fromVehicle)
                .toList();
    }

    public List<VehicleDto> findByQuery(String query) {
        return vehicleRepository.findByQuery(query).stream()
                .map(VehicleDto::fromVehicle)
                .toList();
    }

    public boolean isPlateNumberExists(String plateNumber) {
        return vehicleRepository.findByPlateNumber(plateNumber).isPresent();
    }

    public VehicleDto findById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).get();
        return VehicleDto.fromVehicle(vehicle);
    }

    public List<VehicleDto> getClientVehicles(Long clientId) {
        List<Vehicle> vehicleList = vehicleRepository.findByClientId(clientId);
        return vehicleList.stream()
                .map(VehicleDto::fromVehicle)
                .toList();
    }

    @Transactional
    public void createServiceOrder(Long vehicleId, CreateServiceOrderDto createServiceOrderDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        ServiceOrder serviceOrder = createServiceOrderDto.toServiceOrder();
        serviceOrder.setVehicle(vehicle);
        serviceOrderRepository.save(serviceOrder);
    }

    @Transactional
    public void updateVehicle(long vehicleId, VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        vehicle.setMake(vehicleDto.getMake());
        vehicle.setPlateNumber(vehicleDto.getPlateNumber());
        vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findByPlateNumber(String plateNumber) {
        return vehicleRepository.findByPlateNumber(plateNumber);
    }
}
