package com.infoshareacademy.dao.client;

import com.infoshareacademy.dao.Dao;
import com.infoshareacademy.entity.client.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    @Override
    public void save(Client client) {
        entityManager.persist(client);
    }

    @Override
    public Client update(Client client) {
        return entityManager.merge(client);
    }

    @Override
    public void delete(Client client) {
        if (client != null) {
            entityManager.remove(client);
        }
    }

    public Optional<Client> findByEmail(String email) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c where c.email = :email", Client.class)
                .setParameter("email", email);

        return query.getResultStream().findFirst();
    }

    public List<Client> findByQuery(String query) {
        return entityManager.createQuery("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(:query) OR LOWER(c.email) LIKE LOWER(:query)", Client.class)
                .setParameter("query", "%" + query + "%").getResultList();
    }
}
