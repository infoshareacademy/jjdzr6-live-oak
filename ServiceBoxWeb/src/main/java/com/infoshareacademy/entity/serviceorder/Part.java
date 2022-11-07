package com.infoshareacademy.entity.serviceorder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "part")
@Getter
@Setter
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String partName;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Part(String partName, Double cost, Integer quantity) {
        this.partName = partName;
        this.cost = cost;
        this.quantity = quantity;
    }
}
