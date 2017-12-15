package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.BaseEntity;
import com.onyas.hibernate.util.ReflectionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public abstract class BaseShardRepository<T extends BaseEntity>{

    protected Class<T> clazz;

    public BaseShardRepository() {
        this.clazz = ReflectionUtil.getClassGenericType(getClass());
    }

    public void setupEntityClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Session getSession() {
        return null;
    }

    @Transactional
    public T save(T object) {
        checkTableShard(object);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(object);
        session.flush();
        tx.commit();
        session.close();
        return object;
    }

    protected abstract void checkTableShard(T object);

    @Transactional
    public T update(final T object) {
        checkTableShard(object);
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(object);
        session.flush();
        tx.commit();
        session.close();
        return object;
    }

    @Transactional
    public T saveOrUpdate(final T object) {
        checkTableShard(object);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(object);
        session.flush();
        tx.commit();
        session.close();
        return object;
    }

    @Transactional
    public void delete(final T object) {
        checkTableShard(object);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.delete(object);
        session.flush();

        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public T findById(final T object,Long id) {
        checkTableShard(object);
        return (T) getSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getSession().createCriteria(clazz).list();
    }

    public List<T> findByOwnerId(final T object,long ownerId) {
        checkTableShard(object);
        return (List<T>) getSession().createQuery("from User where ownerid = ?0 ")
                .setParameter(0, ownerId)
                .list();
    }

}
