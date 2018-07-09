package com.test.onyas.hibernate.service;

import com.onyas.hibernate.dao.Book;
import com.onyas.hibernate.service.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRepoTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testAdd() {
        for (int i = 0; i < 51; i++) {
            Book book = new Book();
            book.setAccessToken("accessTokenByAdd");
            book.setUserName("user");
            book.setRefreshToken("s");
            book.setOwnerId(12);
            bookRepository.save(book);
        }

    }
}
