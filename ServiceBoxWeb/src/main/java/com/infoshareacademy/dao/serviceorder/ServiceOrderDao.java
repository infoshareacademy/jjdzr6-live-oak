package com.infoshareacademy.dao.serviceorder;

import com.infoshareacademy.dao.Dao;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        TypedQuery<ServiceOrder> query = entityManager.createQuery("select s from ServiceOrder s", ServiceOrder.class);
        return query.getResultList();
    }

    @Override
    public void save(ServiceOrder serviceOrder) {
        entityManager.persist(serviceOrder);
    }

    @Override
    public ServiceOrder update(ServiceOrder serviceOrder) {
        entityManager.merge(serviceOrder);
        return serviceOrder;
    }

    @Override
    public void delete(ServiceOrder serviceOrder) {
        entityManager.remove(serviceOrder);
    }
}
