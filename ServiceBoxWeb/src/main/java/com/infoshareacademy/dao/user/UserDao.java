package com.infoshareacademy.dao.user;

import com.infoshareacademy.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                        "SELECT u from User u WHERE u.username = :username", User.class)
                .setParameter("username", username);

        List<User> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }
}
