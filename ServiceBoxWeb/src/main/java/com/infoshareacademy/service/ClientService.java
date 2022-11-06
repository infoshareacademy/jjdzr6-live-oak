package com.infoshareacademy.service;

import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.dto.client.CreateClientDto;
import com.infoshareacademy.dto.vehicle.CreateVehicleDto;
import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.user.Role;
import com.infoshareacademy.entity.user.User;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.repository.ClientRepository;
import com.infoshareacademy.repository.RoleRepository;
import com.infoshareacademy.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
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

    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> page = clientRepository.findAll(pageable);

        List<ClientDto> clientsOnPage = page.stream()
                .map(ClientDto::fromClient)
                .toList();

        return new PageImpl<>(clientsOnPage, pageable, page.getTotalElements());
    }

    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).get();
        return ClientDto.fromClient(client);
    }

    public Page<ClientDto> findByQuery(String query, Pageable pageable) {
        Page<Client> page = clientRepository.findByQuery(query, pageable);

        List<ClientDto> clientsOnPage = page.stream()
                .map(ClientDto::fromClient)
                .toList();

        return new PageImpl<>(clientsOnPage, pageable, page.getTotalElements());
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
        Optional<Client> clientFromDb = clientRepository.findById(clientId);

        if (clientFromDb.isEmpty()) {
            throw new IllegalArgumentException("Client with ID " + clientId + " not exists");
        }

        Client client = clientFromDb.get();
        Role role = roleRepository.findRoleByName("ROLE_CLIENT");

        // create account
        User account = new User(client.getEmail(), passwordEncoder.encode(plainPassword), role);

        // add account to client and update
        client.setUser(account);
        clientRepository.save(client);
    }

}
