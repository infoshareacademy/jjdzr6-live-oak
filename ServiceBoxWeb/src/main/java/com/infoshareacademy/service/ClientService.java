package com.infoshareacademy.service;

import com.infoshareacademy.dao.user.RoleDao;
import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.dto.client.CreateClientDto;
import com.infoshareacademy.dto.vehicle.CreateVehicleDto;
import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.user.Role;
import com.infoshareacademy.entity.user.User;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.repository.ClientRepository;
import com.infoshareacademy.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final RoleDao roleDao;
    private final VehicleRepository vehicleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addClient(CreateClientDto createClientDto) {
        Client client = createClientDto.toClient();
        clientRepository.save(client);
    }

    @Transactional
    public void updateClient(long id, ClientDto clientDto) {
        Client client = clientRepository.findById(id).get();
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setNip(clientDto.getNip());
        client.setPhoneNumber(clientDto.getPhoneNumber());

        Address clientAddress = null;

        if (clientDto.getAddress() != null) {
            clientAddress = new Address(
                    clientDto.getAddress().getStreet(),
                    clientDto.getAddress().getHouseNumber(),
                    clientDto.getAddress().getFlatNumber(),
                    clientDto.getAddress().getZipCode(),
                    clientDto.getAddress().getCity()
            );
        }
        client.setAddress(clientAddress);

        clientRepository.save(client);
    }

    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromClient)
                .toList();
    }

    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).get();
        return ClientDto.fromClient(client);
    }

    public List<ClientDto> findByQuery(String query) {
        return clientRepository.findByQuery(query).stream()
                .map(ClientDto::fromClient)
                .toList();


    }

    public boolean isEmailExists(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void addVehicle(Long clientId, CreateVehicleDto createVehicleDto) {
        Client client = clientRepository.findById(clientId).get();
        Vehicle vehicle = createVehicleDto.toVehicle();
        vehicle.setClient(client);
        vehicleRepository.save(vehicle);
    }

    @Transactional
    public void createUserAccount(Long clientId, String plainPassword) {
        Client client = clientRepository.findById(clientId).get();
        Role role = roleDao.findByName("ROLE_CLIENT");

        // create account
        User account = new User(client.getEmail(), passwordEncoder.encode(plainPassword), role);

        // add account to client and update
        client.setUser(account);
        clientRepository.save(client);
    }

}
