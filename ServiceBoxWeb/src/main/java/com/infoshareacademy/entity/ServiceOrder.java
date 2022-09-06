package com.infoshareacademy.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode

public class ServiceOrder extends Entity {
    private LocalDate createdAt = LocalDate.now();
    private LocalDate finishedAt;
    private ServiceOrderState state = ServiceOrderState.CREATED;

    @NotBlank
    private String orderNumber;

    @NotNull
    private boolean onlyNewParts;

    @NotNull
    @Min(1)
    private double maxCost;

    @NotBlank
    @Size(min = 8, max = 400)
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
