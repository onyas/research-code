package com.onyas.web.togglz;

import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.user.UserProvider;

import java.io.File;

@Component
public class MyTogglzConfiguration implements TogglzConfig {

    public Class<? extends Feature> getFeatureClass() {
        return MyFeatures.class;
    }

    @Override
    public StateRepository getStateRepository() {
        return new FileBasedStateRepository(new File("/tmp/features.properties"));
    }

    @Override
    public UserProvider getUserProvider() {
        /*return () -> {
            SimpleFeatureUser user = new SimpleFeatureUser("admin", true);
            user.setAttribute("ownerId", "123");
            return user;
        };*/
        return new Archiver52UserProvider();
    }

}