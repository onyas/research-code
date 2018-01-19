package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

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

    @Transactional
    public List<User> bulkSave(final List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return Collections.emptyList();
        }
        List<User> resultList = getSession().doReturningWork(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into test_user_0 (accessToken, ownerId, refreshToken, threadName, userName) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    for (User user : userList) {
                        ps.setString(1, user.getAccessToken());
                        ps.setLong(2, user.getOwnerId());
                        ps.setString(3, user.getRefreshToken());
                        ps.setString(4, user.getThreadName());
                        ps.setString(5, user.getUserName());
                        ps.executeUpdate();
                        ResultSet rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            int id = rs.getInt(1);
                            user.setId(Long.valueOf(id));
                        }
                    }
                    return userList;
                }
        );
        return resultList;
    }
}
