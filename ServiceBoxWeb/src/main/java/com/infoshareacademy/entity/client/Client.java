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

    // fetch EAGER (default)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @Column(name = "nip", columnDefinition = "varchar(10)")
    private String nip;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "allow_notifications", columnDefinition = "boolean default false")
    private boolean allowNotifications = false;

    // fetch LAZY (default)
    @OneToMany(mappedBy = "client")
    private List<Vehicle> vehicles;

    public Client(String name, String nip, String phoneNumber, String email, boolean allowNotifications) {
        this.name = name;
        this.nip = nip;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.allowNotifications = allowNotifications;
    }

    public Client(String name, Address address, String nip, String phoneNumber, String email, boolean allowNotifications) {
        this.name = name;
        this.address = address;
        this.nip = nip;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.allowNotifications = allowNotifications;
    }
}
