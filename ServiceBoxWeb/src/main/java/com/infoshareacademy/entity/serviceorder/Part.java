package com.infoshareacademy.entity.serviceorder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Part implements RepairItem {
    private String partName;
    private double cost;
    private int quantity;

    public Part() {
    }

    public Part(String partName, double cost, int quantity) {
        this.partName = partName;
        this.cost = cost;
        this.quantity = quantity;
    }

    @Override
    public double calculateTotalCost() {
        return quantity * cost;
    }
}
