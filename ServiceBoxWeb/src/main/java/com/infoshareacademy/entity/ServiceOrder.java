package com.infoshareacademy.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode

public class ServiceOrder extends Entity {
    private LocalDate createdAt = LocalDate.now();
    private LocalDate finishedAt;
    private ServiceOrderState state = ServiceOrderState.CREATED;
    private String orderNumber;
    private boolean onlyNewParts;
    private double maxCost;
    private String description;

    // foreign key (one-to-one)
    private int vehicleId;
    private RepairCard repairCard;

    public ServiceOrder() {
    }

    public ServiceOrder(Vehicle vehicle, String orderNumber, boolean onlyNewParts, float maxCost, String description) {
        this.onlyNewParts = onlyNewParts;
        this.maxCost = maxCost;
        this.description = description;
        this.vehicleId = vehicle.getId();
        this.orderNumber = orderNumber;

        createdAt = LocalDate.now();
        state = ServiceOrderState.CREATED;
    }

    public void addRepairCard(RepairCard repairCard) {
        this.repairCard = repairCard;
    }
}
