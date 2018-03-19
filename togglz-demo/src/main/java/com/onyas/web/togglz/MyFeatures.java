package com.onyas.web.togglz;


import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.metadata.FeatureMetaData;
import org.togglz.core.spi.ActivationStrategy;

import java.util.List;

public enum MyFeatures implements Feature {

    @EnabledByDefault
    @Label("Gradual_rollout")
    Gradual_rollout,

    @Label("Ip address client")
    Ip_address,

    @Label("Ip address server")
    Ip_address_s,

    @Label("Release date")
    Release_date,

    @Label("System property")
    System_property,

    @Label("User by name")
    User_by_name,

    @Label("User by role")
    User_by_role,

    @Label("Archiver 5.2")
    Archiver_5_2,
    ;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

    public FeatureMetaData metaData() {
        return FeatureContext.getFeatureManager().getMetaData(this);
    }
}