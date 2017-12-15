package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.BaseEntity;
import com.onyas.hibernate.util.ReflectionUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public abstract class BaseRepository<T extends BaseEntity> {
    protected Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public BaseRepository() {
        this.clazz = ReflectionUtil.getClassGenericType(getClass());
    }

    public void setupEntityClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public T save(final T object) {
        getSession().setFlushMode(FlushMode.AUTO);
        getSession().persist(object);
        getSession().flush();
        return object;
    }

    @Transactional
    public T update(final T object) {
        getSession().setFlushMode(FlushMode.AUTO);
        getSession().update(object);
        getSession().flush();
        return object;
    }

    @Transactional
    public T saveOrUpdate(final T object) {
        getSession().setFlushMode(FlushMode.AUTO);
        getSession().saveOrUpdate(object);
        getSession().flush();
        return object;
    }

    @Transactional
    public void delete(final T object) {
        getSession().setFlushMode(FlushMode.AUTO);
        getSession().delete(object);
        getSession().flush();
    }

    @SuppressWarnings("unchecked")
    public T findById(final Long id) {
        return (T) getSession().get(clazz, id);
    }

    @Transactional
    public List<T> findAll() {
        return getSession().createCriteria(clazz).list();
    }

}
