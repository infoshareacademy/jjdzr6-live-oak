package com.infoshareacademy.dao.vehicle;

import com.infoshareacademy.dao.Dao;
import com.infoshareacademy.entity.vehicle.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleDao implements Dao<Vehicle> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Vehicle findById(Long id) {
        return entityManager.find(Vehicle.class, id);
    }

    @Override
    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = entityManager.createQuery("SELECT v FROM Vehicle v ", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public void save(Vehicle vehicle) {
        entityManager.persist(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return entityManager.merge(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        if (vehicle != null) {
            entityManager.remove(vehicle);
        }
    }

    public List<Vehicle> findByQuery(String query) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE LOWER(v.plateNumber) LIKE LOWER(:query)", Vehicle.class)
                .setParameter("query", "%" + query + "%").getResultList();
    }

    public Optional<Vehicle> findByPlateNumber(String plateNumber) {
        TypedQuery<Vehicle> query = entityManager.createQuery("SELECT v FROM Vehicle v where LOWER(v.plateNumber) = LOWER(:plateNumber)", Vehicle.class)
                .setParameter("plateNumber", plateNumber);

        return query.getResultStream().findFirst();
    }

    public List<Vehicle> findByClientId(Long id) {
        TypedQuery<Vehicle> query = entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.client.id = :id", Vehicle.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
