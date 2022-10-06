package com.infoshareacademy.dao.user;

import com.infoshareacademy.entity.user.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                        "SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name);

        List<Role> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }
}
