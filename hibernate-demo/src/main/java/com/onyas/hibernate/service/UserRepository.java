package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository extends BaseRepository<User> {

    @Transactional
    public int updateToCurrentThread(User user) {
        return getSession()
                .createQuery("update User set threadName = ?0 where id = ?1 and threadName is null")
                .setParameter(0, user.getThreadName())
                .setParameter(1, user.getId())
                .executeUpdate();
    }


    @Transactional
    public int batchUpdateToCurrentThread(List<Long> userIds, String name) {
        return getSession()
                .createNativeQuery("update test_user set threadName = ? where id in ( :ids ) and threadName is null")
                .setParameter(1, name)
                .setParameterList("ids", userIds)
                .executeUpdate();
    }
}
