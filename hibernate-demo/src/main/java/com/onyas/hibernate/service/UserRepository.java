package com.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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
}
