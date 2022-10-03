package com.infoshareacademy.dto.vehicle;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * A DTO for the {@link Vehicle} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateVehicleDto {
    @NotBlank(message = "To pole jest wymagane")
    private String make;
    @NotBlank(message = "To pole jest wymagane")
    private String model;
    @NotBlank(message = "To pole jest wymagane")
    private String plateNumber;
    private Double engineCapacity;
    private Integer productionYear;
    private Integer mileage;
    @Pattern(regexp="(^$|[\\da-zA-Z]{17})", message = "Niepoprawny VIN")
    private String vin;

    public Vehicle toVehicle() {
        return new Vehicle(
                this.make,
                this.model,
                this.plateNumber,
                this.engineCapacity,
                this.productionYear,
                this.mileage,
                this.vin
        );
    }
}