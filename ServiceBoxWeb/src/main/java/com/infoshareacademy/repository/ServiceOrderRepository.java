package com.infoshareacademy.repository;

import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    @Query("SELECT so FROM ServiceOrder so WHERE LOWER(so.orderNumber) LIKE LOWER(concat('%',:query,'%'))")
    Page<ServiceOrder> findByQuery(String query, Pageable pageable);

    Optional<ServiceOrder> findByOrderNumber(String orderNumber);

    @Query("SELECT count(so) FROM ServiceOrder so WHERE so.state = :state")
    Long countByState(ServiceOrderState state);

    List<ServiceOrder> findFirst3ByOrderByIdDesc();

    Page<ServiceOrder> findServiceOrderByState(ServiceOrderState state, Pageable pageable);

}