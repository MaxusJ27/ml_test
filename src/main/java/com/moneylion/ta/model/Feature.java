package com.moneylion.ta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "features")
public class Feature {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    // default constructor
    private Feature() {}
    // Constructors, getters
    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getId() {
        return id;
    }
    
    // for logging
    @Override 
    public String toString() {
        return String.format("Feature[id=%d, name='%s', description='%s']", id, name, description);
    }
}