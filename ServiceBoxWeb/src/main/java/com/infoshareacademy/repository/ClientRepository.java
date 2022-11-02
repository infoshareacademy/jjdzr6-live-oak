package com.infoshareacademy.repository;

import com.infoshareacademy.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(concat('%',:query,'%')) OR LOWER(c.email) LIKE LOWER(concat('%',:query,'%'))")
    List<Client> findByQuery(String query);

    Optional<Client> findByEmail(String email);
}