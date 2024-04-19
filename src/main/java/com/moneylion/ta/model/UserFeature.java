package com.moneylion.ta.model;

import jakarta.persistence.*;
// UserFeature entity that links to User and Feature table
@Entity 
public class UserFeature {
    // default constructor
    private UserFeature() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id") // link to User table by user id
    private User user;

    @ManyToOne
    @JoinColumn(name = "feature_id") // link to Feature table by feature id
    private Feature feature;

    private boolean access;
    // constructors, getters, setters
    public UserFeature(User user, Feature feature, boolean access) {
        this.user = user;
        this.feature = feature;
        this.access = access;
    }

    public User getUser() {
        return this.user;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public boolean getAccess() {
        return this.access;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public void setFeature(Feature feature) {
        this.feature = feature;
    }
    public void setAccess(boolean access) {
        this.access = access;
    }
    
    // for logging
    @Override
    public String toString() {
        return String.format("UserFeature[id=%d, user='%s', feature='%s', access='%b']", id, user, feature, access);
    }
}
