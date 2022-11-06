package com.infoshareacademy.dto.vehicle;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * A DTO for the {@link com.infoshareacademy.entity.vehicle.Vehicle} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private Long id;
    @NotBlank(message = "To pole jest wymagane")
    private String make;
    @NotBlank(message = "To pole jest wymagane")
    private String model;
    @NotBlank(message = "To pole jest wymagane")
    private String plateNumber;
    private Double engineCapacity;
    private Integer productionYear;
    private Integer mileage;
    @Pattern(regexp="(^$|[\\da-zA-Z]{17})", message = "Niepoprawny VIN. Wymagane 17 znaków.")
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