package com.infoshareacademy.repository;

import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    @Query("SELECT so FROM ServiceOrder so WHERE LOWER(so.orderNumber) LIKE LOWER(concat('%',:query,'%'))")
    List<ServiceOrder> findByQuery(String query);

    Optional<ServiceOrder> findByOrderNumber(String orderNumber);

    @Query("SELECT count(so) FROM ServiceOrder so WHERE so.state = :state")
    Long countServiceOrderWithState(ServiceOrderState state);

    @Query("SELECT so FROM ServiceOrder so ORDER BY so.id DESC")
    List<ServiceOrder> getLastServiceOrders(int limit);

}