package com.infoshareacademy.entity.serviceorder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "repair_card")
@Getter
@Setter
@NoArgsConstructor
public class RepairCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    @JoinColumn(name = "repair_card_id")
    private Set<Repair> repairs;

    @OneToMany
    @JoinColumn(name = "repair_card_id")
    private Set<Part> parts;
}
