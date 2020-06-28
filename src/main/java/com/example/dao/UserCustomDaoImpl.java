package com.example.dao;

import com.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserCustomDaoImpl implements UserCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createNativeQuery("select * from tab_users where email = ?", User.class);
        query.setParameter(1, email);
        return (User) query.getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createNativeQuery("select * from tab_users where username = ?", User.class);
        query.setParameter(1, username);
        return (User) query.getSingleResult();
    }
}
