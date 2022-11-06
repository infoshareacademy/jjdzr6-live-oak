package com.infoshareacademy.repository;

import com.infoshareacademy.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.plateNumber) LIKE LOWER(concat('%',:query,'%'))")
    Page<Vehicle> findByQuery(String query, Pageable pageable);

    Optional<Vehicle> findByPlateNumber(String plateNumber);

    List<Vehicle> findByClientId(Long clientId);
}