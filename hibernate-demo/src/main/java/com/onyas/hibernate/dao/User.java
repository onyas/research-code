package com.onyas.hibernate.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_user")
public class User implements Serializable,BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "ownerId", unique = true)
    private long ownerId;

    @Column(name = "refreshToken", length = 1000)
    private String refreshToken;

    @Column(name = "accessToken", length = 1000)
    private String accessToken;

    @Column(name = "userName")
    private String userName;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
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
}
