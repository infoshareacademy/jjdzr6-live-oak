package com.infoshareacademy.dao.serviceorder;

import com.infoshareacademy.dao.Dao;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.vehicle.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ServiceOrderDao implements Dao<ServiceOrder> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ServiceOrder find(Long id) {
        return entityManager.find(ServiceOrder.class, id);
    }

    @Override
    public List<ServiceOrder> findAll() {
        TypedQuery<ServiceOrder> query = entityManager.createQuery("SELECT s FROM ServiceOrder s", ServiceOrder.class);
        return query.getResultList();
    }

    @Override
    public void save(ServiceOrder serviceOrder) {
        entityManager.persist(serviceOrder);
    }

    @Override
    public ServiceOrder update(ServiceOrder serviceOrder) {
        return entityManager.merge(serviceOrder);
    }

    @Override
    public void delete(ServiceOrder serviceOrder) {
        if (serviceOrder != null) {
            entityManager.remove(serviceOrder);
        }
    }

    public List<ServiceOrder> findByQuery(String query) {
        return entityManager.createQuery("SELECT so FROM ServiceOrder so WHERE LOWER(so.orderNumber) LIKE LOWER(:query)", ServiceOrder.class)
                .setParameter("query", "%" + query + "%").getResultList();
    }

    public Long countServiceOrders() {
        Query query = entityManager.createQuery("SELECT count(so) FROM ServiceOrder so");
        return (Long) query.getSingleResult();
    }
}
