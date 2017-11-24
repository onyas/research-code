package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User>{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
