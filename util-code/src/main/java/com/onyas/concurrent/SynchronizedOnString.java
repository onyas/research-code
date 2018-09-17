package com.onyas.concurrent;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SynchronizedOnString {

    private static Interner<String> pool = Interners.newWeakInterner();
    private static int threadNum = 5;
    static List<Integer> data = Lists.newArrayList();

    public static void main(String[] args) throws InterruptedException {


        List<SynchronizedStringThread> threads = Lists.newArrayList();
        String email = RandomStringUtils.random(5);

        for (int i = 0; i < threadNum; i++) {
            User user = new User();
//        user.setEmail("test@baidu.com");
            user.setEmail(email);
            threads.add(new SynchronizedStringThread(user, i));
            data.add(i);
        }


        for (int i = 0; i < threadNum; i++) {
            threads.get(i).start();
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(data.size());
    }

    static class SynchronizedStringThread extends Thread {
        private User user;
        private Integer index;

        public SynchronizedStringThread(User user, Integer index) {
            this.user = user;
            this.index = index;
        }

        @Override
        public void run() {

//            synchronized (pool.intern(user.getEmail())) {
            synchronized (user.getEmail()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
//                    TimeUnit.SECONDS.sleep(1);
                    data.remove(index);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class User {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
