package com.infoshareacademy.entity.client;

import com.infoshareacademy.entity.user.User;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "nip", columnDefinition = "varchar(10)")
    private String nip;

    @Column(name = "phone", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "notifications", columnDefinition = "boolean default false")
    private boolean allowNotify = false;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;
}
