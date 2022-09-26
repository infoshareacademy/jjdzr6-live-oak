package com.infoshareacademy.entity.serviceorder;

import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceOrderState state = ServiceOrderState.CREATED;

    @Column(name = "new_parts", columnDefinition = "boolean default false")
    private boolean onlyNewParts;

    @Column(name = "max_cost", nullable = false)
    private Integer maxCost;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "repair_card_id")
    private RepairCard repairCard;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id")
    private List<Notes> notes;
}


