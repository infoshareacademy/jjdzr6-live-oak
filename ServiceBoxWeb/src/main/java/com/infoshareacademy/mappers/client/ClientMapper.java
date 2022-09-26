package com.infoshareacademy.mappers.client;

import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.entity.client.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public ClientDto toDto(Client client) {

        return new ClientDto(client.getId(), client.getName(), client.getAddress(), client.getNip(), client.getPhoneNumber(), client.getEmail(), client.isAllowNotify());
    }
}
