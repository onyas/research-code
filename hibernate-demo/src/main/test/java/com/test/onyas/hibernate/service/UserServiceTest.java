package com.test.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import com.onyas.hibernate.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;


@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration()
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        User user = new User();
        user.setAccessToken("test");
        int ownId = new Random().nextInt(100000);
        user.setOwnerId(ownId);
        userRepository.save(user);
    }


    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setAccessToken("test");
        user.setOwnerId(47597);
        user.setRefreshToken("update");
        userRepository.update(user);
    }

    @Test
    public void testSaveOrUpdate() {
        User user = new User();
        user.setId(1L);
        user.setAccessToken("test");
        user.setOwnerId(47597);
        user.setRefreshToken("update");
        userRepository.saveOrUpdate(user);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setId(1L);
        user.setOwnerId(47597);
        userRepository.delete(user);
    }

}
