package com.infoshareacademy.dto.serviceOrder;

import com.infoshareacademy.entity.serviceorder.RepairCard;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class ServiceOrderDto {

    private Long id;
    private String orderNumber;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private ServiceOrderState state = ServiceOrderState.CREATED;
    private boolean onlyNewParts;
    private Integer maxCost;
    private String description;
    private String plateNumber;
    private Long cardId;

}
