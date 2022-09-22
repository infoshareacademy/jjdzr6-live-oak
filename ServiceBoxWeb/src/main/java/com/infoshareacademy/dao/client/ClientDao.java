package com.infoshareacademy.dao.client;

import com.infoshareacademy.dao.Dao;
import com.infoshareacademy.entity.client.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientDao implements Dao<Client> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Client find(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public List<Client> findAll() {
        TypedQuery<Client> query = entityManager.createQuery("select c from Client c", Client.class);
        return query.getResultList();
    }

    @Override
    public void save(Client client) {
        entityManager.persist(client);
    }

    @Override
    public Client update(Client client) {
        entityManager.merge(client);
        return client;
    }

    @Override
    public void delete(Client client) {
        if (client != null) {
            entityManager.remove(client);
        }
    }
}
