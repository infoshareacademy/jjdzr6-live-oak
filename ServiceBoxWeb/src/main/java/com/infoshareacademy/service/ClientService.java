package com.infoshareacademy.service;

import com.infoshareacademy.entity.Client;
import com.infoshareacademy.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }


    public Client findClientById(int id) {
        return clientRepository.find(id);
    }

    public void addClient(Client client) {
        clientRepository.add(client);
        try {
            clientRepository.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Client findClient(int id) {
        return clientRepository.find(id);
    }

    public List<Client> findByQuery(String query) {
        return clientRepository.findBy(query);
    }

}
