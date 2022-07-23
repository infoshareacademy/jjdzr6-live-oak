package com.infoshareacademy.entity;

import lombok.Data;

@Data
public class Repair implements RepairItem {
    private int id;
    private String description;
    private float cost;

    @Override
    public float getTotalCost() {
        return cost;
    }
}
