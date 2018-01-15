package com.test.onyas.hibernate.service;

import com.onyas.hibernate.dao.User;
import com.onyas.hibernate.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setAccessToken("accessTokenByAdd");
            user.setUserName("user" + i);
            user.setRefreshToken("s");
            user.setOwnerId(i);
            userRepository.save(user);
        }
    }


    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(10015L);
        user.setUserName("update10015");
        user.setOwnerId(10015);
        userRepository.update(user);
    }

    @Test
    public void testSaveOrUpdate() {
        User user = new User();
        user.setId(10016L);
        user.setUserName("update10015");
        user.setOwnerId(10016);
        userRepository.saveOrUpdate(user);
    }

    @Test
    public void testUpdateToCurrentThread() {
        User user = new User();
        user.setId(152L);
        user.setThreadName("test");
        int result = userRepository.updateToCurrentThread(user);
        System.out.println("result = " + result);
    }

    @Test
    public void testLock() throws Exception {
        int endNum = 5;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(endNum);

        for (int i = 0; i < endNum; i++) {
            threadPool.submit(new TableLockThread(start, end, userRepository));
        }

        start.countDown();
        end.await();
        System.out.println("end...");
    }


    static class TableLockThread implements Runnable {
        private CountDownLatch start;
        private CountDownLatch end;
        private UserRepository userRepository;

        public TableLockThread(CountDownLatch start, CountDownLatch end, UserRepository userRepository) {
            this.start = start;
            this.end = end;
            this.userRepository = userRepository;
        }

        @Override
        public void run() {
            try {
                start.await();
                List<User> userList = userRepository.findAll();
                if (CollectionUtils.isEmpty(userList)) {
                    return;
                }

                for (User user : userList) {
                    user.setThreadName(Thread.currentThread().getName());
                    int result = userRepository.updateToCurrentThread(user);
                }
                end.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
