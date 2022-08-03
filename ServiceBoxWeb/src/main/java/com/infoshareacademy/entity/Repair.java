package com.infoshareacademy.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Repair implements RepairItem {
    private String description;
    private double cost;

    public Repair() {
    }

    public Repair(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    @Override
    public double calculateTotalCost() {
        return cost;
    }
}
