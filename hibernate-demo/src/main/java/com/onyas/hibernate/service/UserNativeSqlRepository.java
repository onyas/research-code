package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class UserNativeSqlRepository extends BaseRepository<User> {

    @Transactional
    public User save(final User user) {
        getSession().createNativeQuery("insert into test_user_0 (accessToken, ownerId, refreshToken, threadName, userName) values (?, ?, ?, ?, ?)")
                .setParameter(1, user.getAccessToken())
                .setParameter(2, user.getOwnerId())
                .setParameter(3, user.getRefreshToken())
                .setParameter(4, user.getThreadName())
                .setParameter(5, user.getUserName())
                .executeUpdate();
        return user;
    }


    @Transactional
    public User findByOwnerId(long ownerId) {
        long tableId = ownerId % 3;
        String sql = "select * from test_user_" + tableId + " where ownerId =:ownerId";
        return getSession().createNativeQuery(sql, User.class)
                .setParameter("ownerId", ownerId)
                .uniqueResult();
    }
}
