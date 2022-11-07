package com.infoshareacademy.entity.serviceorder;

import com.infoshareacademy.entity.client.Client;
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
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "repair_card_id")
    private Set<Repair> repairs;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "repair_card_id")
    private Set<Part> parts;

    @OneToOne(mappedBy = "repairCard")
    private ServiceOrder serviceOrder;
}
