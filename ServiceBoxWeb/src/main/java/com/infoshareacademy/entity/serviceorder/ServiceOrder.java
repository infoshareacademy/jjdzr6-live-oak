package com.infoshareacademy.entity.serviceorder;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "service_order")
@Getter
@Setter
@NoArgsConstructor

public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "finished_at")
    private LocalDate finishedAt;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceOrderState state;

    @Column(name = "new_parts")
    private boolean onlyNewParts;

    @Column(name = "max_cost")
    private double maxCost;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    //private RepairCard repairCard;


 /*   public void addRepairCard(RepairCard repairCard) {
        this.repairCard = repairCard;
    }*/
}


