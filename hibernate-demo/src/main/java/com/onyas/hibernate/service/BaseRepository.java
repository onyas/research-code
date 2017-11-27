package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.BaseEntity;
import com.onyas.hibernate.dao.User;
import com.onyas.hibernate.interceptor.TableShardInterceptor;
import com.onyas.hibernate.util.ReflectionUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public abstract class BaseRepository<T extends BaseEntity> {
    protected Class<T> clazz;
    protected ThreadLocal<TableShardInterceptor> tableShardInterceptorThreadLocal = new ThreadLocal<>();

    public BaseRepository() {
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
        if (object instanceof User) {
            User user = (User) object;
            System.out.println("User info :" + user);
            int modId = user.getOwnerId() % 3;
            tableShardInterceptorThreadLocal.set(new TableShardInterceptor("test_user", "test_user_" + modId));
        }
        getSession().save(object);
        Transaction tx = getSession().beginTransaction();
        getSession().flush();
        tx.commit();
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

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getSession().createCriteria(clazz).list();
    }

}
