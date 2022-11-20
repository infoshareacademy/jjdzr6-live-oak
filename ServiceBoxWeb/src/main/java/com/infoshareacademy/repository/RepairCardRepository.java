package com.infoshareacademy.repository;

import com.infoshareacademy.entity.serviceorder.RepairCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairCardRepository extends JpaRepository<RepairCard, Long> {
}