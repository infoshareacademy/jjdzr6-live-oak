package com.infoshareacademy.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

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

}
