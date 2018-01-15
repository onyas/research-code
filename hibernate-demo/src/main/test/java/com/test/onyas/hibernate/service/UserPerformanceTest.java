package com.test.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import com.onyas.hibernate.service.UserNativeSqlRepository;
import com.onyas.hibernate.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserPerformanceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNativeSqlRepository userNativeSqlRepository;

    @Test
    public void testAddHQL() {
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setAccessToken("accessTokenByAdd");
            user.setUserName("user" + i);
            user.setRefreshToken("s");
            user.setOwnerId(i);
            userRepository.save(user);
        }
    }

    @Test
    public void testAddNative() {
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setAccessToken("accessTokenByAdd");
            user.setUserName("user" + i);
            user.setRefreshToken("s");
            user.setOwnerId(i);
            userNativeSqlRepository.save(user);
        }
    }
}
