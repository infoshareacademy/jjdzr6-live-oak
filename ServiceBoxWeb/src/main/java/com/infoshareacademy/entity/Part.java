package com.infoshareacademy.entity;

import lombok.Data;

@Data
public class Part implements RepairItem {
    private int id;
    private String partName;
    private float cost;
    private int quantity;

    @Override
    public float getTotalCost() {
        return quantity * cost;
    }
}
