package com.onyas.web.togglz;

import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.util.HttpServletRequestHolder;

import javax.servlet.http.HttpServletRequest;

public class Archiver52UserProvider implements UserProvider {
    private final boolean featureAdmin;
    private final String userName;

    public Archiver52UserProvider() {
        this("", true);
    }

    public Archiver52UserProvider(String ownerId) {
        this(ownerId, true);
    }

    public Archiver52UserProvider(String userName, boolean featureAdmin) {
        this.userName = userName;
        this.featureAdmin = featureAdmin;
    }

    @Override
    public FeatureUser getCurrentUser() {
        HttpServletRequest request = HttpServletRequestHolder.get();
        String ownerId = (String) request.getAttribute("ownerId");
        System.out.println(ownerId);
        SimpleFeatureUser simpleFeatureUser = new SimpleFeatureUser(userName, featureAdmin);
        simpleFeatureUser.setAttribute("ownerId", ownerId);
        return simpleFeatureUser;
    }
}
