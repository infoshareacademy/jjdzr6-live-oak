package com.infoshareacademy.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
        @Pattern(regexp="(^$|[\\da-zA-Z]{17})", message = "Niepoprawny VIN")
        private String vin;
        private String clientName;
        private Long clientId;
}
