package com.infoshareacademy.dto.client;

import com.infoshareacademy.entity.client.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ClientDto {

    private Long id;
    private String name;
    private Address address;
    private String nip;
    private String phoneNumber;
    private String email;
    private boolean allowNotify;

    public String getClientAddress() {

        return "ul. " + address.getStreet() + " " + address.getHouseNumber()  + " " + address.getFlatNumber() + ", " + address.getZipCode() + " " + address.getCity();

    }

}
