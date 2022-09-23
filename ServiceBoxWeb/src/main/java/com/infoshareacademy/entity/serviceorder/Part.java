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
    private long id;

    @Column(name = "name", nullable = false)
    private String partName;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
