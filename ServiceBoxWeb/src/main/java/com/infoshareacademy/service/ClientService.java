package com.infoshareacademy.service;

import com.infoshareacademy.dao.client.ClientDao;
import com.infoshareacademy.dao.user.RoleDao;
import com.infoshareacademy.dao.vehicle.VehicleDao;
import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.dto.client.CreateClientDto;
import com.infoshareacademy.dto.client.UpdateClientDto;
import com.infoshareacademy.dto.vehicle.CreateVehicleDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.user.Role;
import com.infoshareacademy.entity.user.User;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientDao clientDao;
    private final RoleDao roleDao;
    private final VehicleDao vehicleDao;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addClient(CreateClientDto createClientDto) {
        Client client = createClientDto.toClient();
        clientDao.save(client);
    }
    @Transactional
    public void updateClient(UpdateClientDto updateClientDto) {
        Client client = updateClient();ClientDto.toClient();
        clientDao.update(client);
    }

    public List<ClientDto> findAll() {
        return clientDao.findAll().stream()
                .map(ClientDto::fromClient)
                .toList();
    }

    public ClientDto findById(Long id) {
        Client client = clientDao.findById(id);
        return ClientDto.fromClient(client);
    }

    public List<ClientDto> findByQuery(String query) {
        return clientDao.findByQuery(query).stream()
                .map(ClientDto::fromClient)
                .toList();
    }

    public boolean isEmailExists(String email) {
        return clientDao.findByEmail(email).isPresent();
    }

    @Transactional
    public void addVehicle(Long clientId, CreateVehicleDto createVehicleDto) {
        Client client = clientDao.findById(clientId);
        Vehicle vehicle = createVehicleDto.toVehicle();
        vehicle.setClient(client);
        vehicleDao.save(vehicle);
    }

    @Transactional
    public void createUserAccount(Long clientId, String plainPassword) {
        Client client = clientDao.findById(clientId);
        Role role = roleDao.findByName("ROLE_CLIENT");

        // create account
        User account = new User(client.getEmail(), passwordEncoder.encode(plainPassword), role);

        // add account to client and update
        client.setUser(account);
        clientDao.update(client);
    }
}
