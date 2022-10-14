package com.infoshareacademy.entity.serviceorder;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service_order")
@Getter
@Setter
@NoArgsConstructor
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "created_at", columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceOrderState state = ServiceOrderState.CREATED;

    @Column(name = "new_parts")
    private boolean onlyNewParts = false;

    @Column(name = "max_cost", nullable = false)
    private Integer maxCost;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "repair_card_id", unique = true)
    private RepairCard repairCard;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_id")
    private List<Note> notes = new ArrayList<>();

    public ServiceOrder(String orderNumber, String description, boolean onlyNewParts, Integer maxCost) {
        this.orderNumber = orderNumber;
        this.onlyNewParts = onlyNewParts;
        this.maxCost = maxCost;
        this.description = description;
    }
}


