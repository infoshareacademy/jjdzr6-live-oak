package com.infoshareacademy.repository;

import com.infoshareacademy.entity.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(concat('%',:query,'%')) OR LOWER(c.email) LIKE LOWER(concat('%',:query,'%'))")
    Page<Client> findByQuery(String query, Pageable pageable);

    Optional<Client> findByEmail(String email);
}