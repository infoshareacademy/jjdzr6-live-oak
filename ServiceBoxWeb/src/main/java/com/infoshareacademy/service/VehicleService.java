package com.infoshareacademy.service;

import com.infoshareacademy.dto.serviceorder.CreateServiceOrderDto;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.repository.ServiceOrderRepository;
import com.infoshareacademy.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private final ServiceOrderRepository serviceOrderRepository;

    public Page<VehicleDto> findAll(Pageable pageable) {
        Page<Vehicle> page = vehicleRepository.findAll(pageable);

        List<VehicleDto> vehiclesOnPage = page.stream()
                .map(VehicleDto::fromVehicle)
                .toList();

        return new PageImpl<>(vehiclesOnPage, pageable, page.getTotalElements());
    }

    public Page<VehicleDto> findByQuery(String query, Pageable pageable) {
        Page<Vehicle> page = vehicleRepository.findByQuery(query, pageable);

        List<VehicleDto> vehiclesOnPage = page.stream()
                .map(VehicleDto::fromVehicle)
                .toList();

        return new PageImpl<>(vehiclesOnPage, pageable, page.getTotalElements());
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
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setPlateNumber(vehicleDto.getPlateNumber());
        vehicle.setEngineCapacity(vehicleDto.getEngineCapacity());
        vehicle.setProductionYear(vehicleDto.getProductionYear());
        vehicle.setVin(vehicleDto.getVin());
        vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findByPlateNumber(String plateNumber) {
        return vehicleRepository.findByPlateNumber(plateNumber);
    }
}
