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
        List<Client> clientList = clientDao.findAll();
        return clientList.stream()
                .map(c->clientMapper.toDto(c))
                .toList();
    }

    @Transactional
    public void addClient(Client client) {
        clientDao.save(client);
    }

    public Client findClient(long id) {
        return clientDao.find(id);
    }

    public List<Client> findByQuery(String query) {
        return new ArrayList<>();
    }

}
