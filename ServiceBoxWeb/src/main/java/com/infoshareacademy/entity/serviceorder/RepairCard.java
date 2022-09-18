package com.infoshareacademy.entity.serviceorder;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RepairCard {
    private Set<Repair> repairs = new HashSet<>();
    private Set<Part> parts = new HashSet<>();

    public RepairCard() {
    }

    public double calculateTotalRepairCost() {
        double totalCost = 0;

        // calculate repair cost
        for (RepairItem repair : repairs) {
            totalCost += repair.calculateTotalCost();
        }

        // calculate parts cost
        for (RepairItem part : parts) {
            totalCost += part.calculateTotalCost();
        }

        return totalCost;
    }

    public void addRepair(Repair repair) {
        repairs.add(repair);
    }

    public void addPart(Part part) {
        parts.add(part);
    }
}
