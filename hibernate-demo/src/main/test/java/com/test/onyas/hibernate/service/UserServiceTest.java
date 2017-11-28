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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration()
public class UserServiceTest {

    static ExecutorService tableThreadPool = Executors.newFixedThreadPool(3);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        User user = new User();
        int ownId = new Random().nextInt(100000);
        user.setOwnerId(ownId);
        user.setAccessToken("test");
        user.setRefreshToken("test_refresh");
        user.setUserName("this is name");
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

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(3L);
        user.setOwnerId(44960);
        user = userRepository.findById(user, 3L);
        System.out.printf("User :" + user);
    }


    @Test
    public void testBatchInsert() {
        int start = new Random().nextInt(500000);
        for (int i = start; i < start + 100; i++) {
            User u = new User();
            u.setOwnerId(i);
            u.setRefreshToken("rt" + i);
            u.setUserName("name" + i);
            u.setAccessToken("ac" + i);
            userRepository.saveOrUpdate(u);
        }
    }

    @Test
    public void testMultiThreadSave() throws InterruptedException {
        int threadNum = 5;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            tableThreadPool.submit(new TableShardInsertThread(start, end));
        }

        System.out.println("start...");
        start.countDown();
        end.await();
        System.out.println("end...");
    }

    class TableShardInsertThread extends Thread {
        private CountDownLatch begin;
        private CountDownLatch end;

        public TableShardInsertThread(CountDownLatch start, CountDownLatch end) {
            this.begin = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                begin.await();
                int start = new Random().nextInt(500000);
                for (int i = start; i < start + 100; i++) {
                    User u = new User();
                    u.setOwnerId(i);
                    u.setRefreshToken("rt" + i);
                    u.setUserName("name" + i);
                    u.setAccessToken("ac" + i);
                    userRepository.saveOrUpdate(u);
                }
                end.countDown();
            } catch (Exception e) {
                System.err.printf("e" + e);
            }
        }
    }


}
