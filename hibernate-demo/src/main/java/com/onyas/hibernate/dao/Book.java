package com.onyas.hibernate.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_book")
public class Book implements Serializable,BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_seq")
    @SequenceGenerator(name = "book_seq",initialValue = 50000000)
    @Column(name = "id")
    protected Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "ownerId")
    private int ownerId;

    @Column(name = "refreshToken", length = 1000)
    private String refreshToken;

    @Column(name = "accessToken", length = 1000)
    private String accessToken;

    @Column(name = "userName")
    private String userName;

    @Column(name = "threadName")
    private String threadName;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", refreshToken='" + refreshToken + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
