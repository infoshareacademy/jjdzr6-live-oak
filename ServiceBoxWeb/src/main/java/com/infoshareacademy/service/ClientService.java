package com.infoshareacademy.service;

import com.infoshareacademy.dao.client.ClientDao;
import com.infoshareacademy.entity.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientDao clientDao;

    public List<Client> findAll() {
        return clientDao.findAll();
    }

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
