package com.infoshareacademy.dto.vehicle;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link com.infoshareacademy.entity.vehicle.Vehicle} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private Long id;
    private String make;
    private String model;
    private String plateNumber;
    private Double engineCapacity;
    private Integer productionYear;
    private Integer mileage;
    private String vin;
    private String clientName;
    private String clientPhoneNumber;

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