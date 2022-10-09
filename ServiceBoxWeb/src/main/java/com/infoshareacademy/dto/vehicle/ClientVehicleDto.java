package com.infoshareacademy.dto.vehicle;

import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientVehicleDto implements Serializable {
    private String plateNumber;
    private List<ServiceOrderDto> serviceOrders;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServiceOrderDto implements Serializable {
        private Long id;
        private String orderNumber;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime finishedAt;
        private ServiceOrderState state = ServiceOrderState.CREATED;
        private boolean onlyNewParts = false;
        private Integer maxCost;
        private String description;
    }

    public static ClientVehicleDto fromVehicle(Vehicle vehicle){
        if (vehicle == null){
            return null;
        }
        return new ClientVehicleDto(vehicle.getPlateNumber(), vehicle.getServiceOrders().stream()
                .map(s->new ServiceOrderDto(
                        s.getId(),
                        s.getOrderNumber(),
                        s.getCreatedAt(),
                        s.getFinishedAt(),
                        s.getState(),
                        s.isOnlyNewParts(),
                        s.getMaxCost(),
                        s.getDescription()
                )).toList());
    }
}
