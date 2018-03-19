package com.onyas.web.togglz.strategy;

import org.springframework.util.StringUtils;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

public class Archiver52 implements ActivationStrategy {
    @Override
    public String getId() {
        return "Archiver5_2";
    }

    @Override
    public String getName() {
        return "Archiver5.2";
    }

    @Override
    public boolean isActive(FeatureState featureState, FeatureUser user) {
        String ownerIds = featureState.getParameter("enabledOwnerIds");
        String ownerId = (String) user.getAttribute("ownerId");
        if (StringUtils.isEmpty(ownerId)) {
            ownerId = "123";
        }
        System.out.println("ownerIds==" + ownerIds + ",ownerId = " + ownerId + ",ownerIds.contains(ownerId) is " + ownerIds.contains(ownerId));
        if (ownerIds.contains(ownerId)) {
            return true;
        }
        return false;
    }

    @Override
    public Parameter[] getParameters() {
        return new Parameter[]{
                ParameterBuilder.create("enabledOwnerIds").label("Enabled ownerIds").largeText().description("A comma-separated list of ownerIds"),
//                ParameterBuilder.create("releaseDate").label("Release Date").optional()
        };
    }
}
