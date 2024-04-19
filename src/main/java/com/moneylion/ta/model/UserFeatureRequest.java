package com.moneylion.ta.model;

public class UserFeatureRequest {
    private UserFeatureRequest() {}
    private String email;
    private String featureName;
    private boolean enable;
    // constructors, getter and setter
    public UserFeatureRequest(String email, String featureName, boolean enable) {
        this.email = email;
        this.featureName = featureName;
        this.enable = enable;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFeatureName() {
        return this.featureName;
    }

    public boolean getEnable() {
        return this.enable;
    }

    @Override
    public String toString() {
        return String.format("UserFeatureRequest[email='%s', featureName='%s', enable='%b']", email, featureName, enable);
    }
    
}
