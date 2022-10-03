package com.infoshareacademy.dto.vehicle;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.infoshareacademy.entity.vehicle.Vehicle} entity
 */
@Data
public class VehicleDto {
    private final Long id;
    private final String make;
    private final String model;
    private final String plateNumber;
    private final Double engineCapacity;
    private final Integer productionYear;
    private final Integer mileage;
    private final String vin;
    private final String clientName;
    private final String clientPhoneNumber;

    public static VehicleDto fromVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        return new VehicleDto(
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getPlateNumber(),
                vehicle.getEngineCapacity(),
                vehicle.getProductionYear(),
                vehicle.getMileage(),
                vehicle.getVin(),
                vehicle.getClient().getName(),
                vehicle.getClient().getPhoneNumber()
        );
    }
}