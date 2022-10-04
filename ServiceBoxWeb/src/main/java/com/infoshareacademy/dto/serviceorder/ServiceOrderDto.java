package com.infoshareacademy.dto.serviceorder;

import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.infoshareacademy.entity.serviceorder.ServiceOrder} entity
 */
@Data
public class ServiceOrderDto {
    private final Long id;
    private final String orderNumber;
    private final LocalDateTime createdAt;
    private final LocalDateTime finishedAt;
    private final ServiceOrderState state;
    private final boolean onlyNewParts;
    private final Integer maxCost;
    private final String description;
    private final String vehiclePlateNumber;
    private final String vehicleClientName;
    private final String vehicleClientPhoneNumber;

    public static ServiceOrderDto fromServiceOrder(ServiceOrder serviceOrder) {
        if (serviceOrder == null) {
            return null;
        }

        return new ServiceOrderDto(
                serviceOrder.getId(),
                serviceOrder.getOrderNumber(),
                serviceOrder.getCreatedAt(),
                serviceOrder.getFinishedAt(),
                serviceOrder.getState(),
                serviceOrder.isOnlyNewParts(),
                serviceOrder.getMaxCost(),
                serviceOrder.getDescription(),
                serviceOrder.getVehicle().getPlateNumber(),
                serviceOrder.getVehicle().getClient().getName(),
                serviceOrder.getVehicle().getClient().getPhoneNumber()
        );
    }
}