package com.infoshareacademy.mappers.vehicle;

import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.vehicle.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleMapper {

    public VehicleDto toDto(Vehicle vehicle){

        return new VehicleDto(vehicle.getId(), vehicle.getMake(), vehicle.getModel(), vehicle.getPlateNumber(),
                vehicle.getEngineCapacity(), vehicle.getProductionYear(), vehicle.getMileage(),
                vehicle.getVin(), vehicle.getClient().getName());
    }
}
