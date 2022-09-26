package com.infoshareacademy.mappers.client;

import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.client.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public ClientDto toDto(Client client) {
        Address address = client.getAddress();
        String clientAddress = "";
        if (address != null) {
            clientAddress = address.getStreet() + " " + address.getHouseNumber() +
                    (address.getFlatNumber() != null ? address.getFlatNumber() : "") + ", " + 
                    address.getZipCode() + " " + address.getCity();
        }

        return new ClientDto(client.getId(), client.getName(), clientAddress, client.getNip(), client.getPhoneNumber(), client.getEmail(), client.isAllowNotify());
    }
}
