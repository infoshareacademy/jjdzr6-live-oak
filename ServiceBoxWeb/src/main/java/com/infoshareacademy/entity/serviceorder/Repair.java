package com.infoshareacademy.entity.serviceorder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "repair")
@Getter
@Setter
@NoArgsConstructor
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost", nullable = false)
    private Double cost;

    public Repair(String description, Double cost) {
        this.description = description;
        this.cost = cost;
    }
}
