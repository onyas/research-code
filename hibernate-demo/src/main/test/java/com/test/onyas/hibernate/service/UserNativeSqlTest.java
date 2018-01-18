package com.test.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import com.onyas.hibernate.service.UserNativeSqlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserNativeSqlTest {

    @Autowired
    private UserNativeSqlRepository userNativeSqlRepository;


    @Test
    public void testFindByOwnerId() {
        User user = userNativeSqlRepository.findByOwnerId(12);
        System.out.println(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setAccessToken("accessTokenByAdd");
        user.setUserName("user");
        user.setRefreshToken("s");
        user.setOwnerId(new Random().nextInt());
        userNativeSqlRepository.save(user);
    }

    @Test
    public void testBulkSave() {
        List<User> userList = new ArrayList<>(1000);
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setAccessToken("accessTokenByAdd" + i);
            user.setUserName("user" + i);
            user.setRefreshToken("s" + i);
            user.setOwnerId(new Random().nextInt());
            userList.add(user);
        }
        userNativeSqlRepository.bulkSave(userList);
    }
}
