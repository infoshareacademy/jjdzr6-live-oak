package com.infoshareacademy.entity.serviceorder;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "created_at", columnDefinition = "DATETIME default CURRENT_DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceOrderState state = ServiceOrderState.CREATED;

    @Column(name = "new_parts", columnDefinition = "boolean default false")
    private boolean onlyNewParts;

    @Column(name = "max_cost", nullable = false)
    private int maxCost;

    @Column(name = "description", nullable = false)
    private String description;

    // FK: vehicle_id -> vehicle (id) / bidirectional
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    //private RepairCard repairCard;


 /*   public void addRepairCard(RepairCard repairCard) {
        this.repairCard = repairCard;
    }*/
}


