package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import com.onyas.hibernate.interceptor.TableShardInspector;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserShardRepository extends BaseShardRepository<User> {
    protected ThreadLocal<TableShardInspector> tableShardInspectorThreadLocal = new ThreadLocal<>();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected Session getSession() {
        if (tableShardInspectorThreadLocal.get() == null) {
            return sessionFactory.getCurrentSession();
        } else {
            SessionBuilder builder = this.sessionFactory.withOptions().statementInspector(tableShardInspectorThreadLocal.get());
            return builder.openSession();
        }
    }

    @Override
    protected void checkTableShard(User user) {
        System.out.println("User info :" + user);
        int modId = user.getOwnerId() % 3;
        tableShardInspectorThreadLocal.set(new TableShardInspector("test_user", "test_user_" + modId));
    }
}
