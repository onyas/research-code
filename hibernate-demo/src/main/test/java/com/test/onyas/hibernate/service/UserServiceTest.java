package com.test.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import com.onyas.hibernate.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
@RunWith( SpringJUnit4ClassRunner.class )
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd(){
        User user = new User();
        user.setId(new Random().nextLong());
        user.setAccessToken("test");
        user.setOwnerId(1);
        userRepository.save(user);
    }


}
