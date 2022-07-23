package com.infoshareacademy.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RepairCard {
    private int id;
    private Set<Repair> repairs = new HashSet<>();
    private Set<Part> parts = new HashSet<>();

    public float getTotalRepairCost()
    {
        float totalCost = 0;

        // calculate repair cost
        for (RepairItem repair : repairs) {
            totalCost += repair.getTotalCost();
        }

        // calculate parts cost
        for (RepairItem part : parts) {
            totalCost += part.getTotalCost();
        }

        return totalCost;
    }
}
