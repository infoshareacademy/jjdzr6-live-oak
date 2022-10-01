package com.infoshareacademy.mappers.client;

import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.client.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setNip(client.getNip());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setEmail(client.getEmail());
        clientDto.setAllowNotify(client.isAllowNotify());

        Address address = client.getAddress();

        if (address != null) {
            clientDto.setAddressStreet(address.getStreet());
            clientDto.setAddressHouseNumber(address.getHouseNumber());
            clientDto.setAddressFlatNumber(address.getFlatNumber());
            clientDto.setAddressZipCode(address.getZipCode());
            clientDto.setAddressCity(address.getCity());
        }

        return clientDto;
    }

    public Client fromDto(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setAllowNotify(clientDto.isAllowNotify());
        client.setNip(clientDto.getNip());

        // add address only if any field provided
        if (clientDto.getAddress().isBlank()) {
            return client;
        }

        Address address = new Address();
        address.setStreet(clientDto.getAddressStreet());
        address.setHouseNumber(clientDto.getAddressHouseNumber());
        address.setFlatNumber(clientDto.getAddressFlatNumber());
        address.setZipCode(clientDto.getAddressZipCode());
        address.setCity(clientDto.getAddressCity());
        client.setAddress(address);

        return client;
    }
}
