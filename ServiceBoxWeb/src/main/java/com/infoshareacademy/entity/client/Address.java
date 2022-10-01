package com.infoshareacademy.entity.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "flat_number")
    private String flatNumber;

    @Column(name = "zip_code", nullable = false, columnDefinition = "VARCHAR(6)")
    private String zipCode;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToOne(optional = false, mappedBy = "address")
    private Client client;

    public void setClient(Client client) {
        this.client = client;
        // set owning side
        client.setAddress(this);
    }
}
