package com.infoshareacademy.service;

import com.infoshareacademy.dao.client.ClientDao;
import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.mappers.client.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    public List<ClientDto> findAll() {
        return clientDao.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Transactional
    public void addClient(ClientDto clientDto) {
        Client client = clientMapper.fromDto(clientDto);
        clientDao.save(client);
    }

    public Client findClient(long id) {
        return clientDao.find(id);
    }

    public List<ClientDto> findByQuery(String query) {
        return clientDao.findByQuery(query).stream()
                .map(clientMapper::toDto)
                .toList();
    }

    public boolean emailExists(String email) {
        return clientDao.findByEmail(email).isPresent();
    }
}
