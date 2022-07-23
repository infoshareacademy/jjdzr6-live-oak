package com.infoshareacademy.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceOrder {
    private int id;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate finishedAt;
    private ServiceOrderState state = ServiceOrderState.CREATED;
    private String orderNumber;
    private boolean onlyNewParts;
    private float maxCost;
    private String description;
    private Vehicle vehicle;
    private RepairCard repairCard;

    public float getFinalCoast()
    {
        return repairCard.getTotalRepairCost();
    }
}
