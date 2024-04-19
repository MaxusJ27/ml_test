package com.moneylion.ta.repository;

import org.springframework.data.repository.CrudRepository;

import com.moneylion.ta.model.Feature;
import org.springframework.data.jpa.repository.Query;

public interface FeatureRepository extends CrudRepository<Feature, Long> {    
    @Query("SELECT f from Feature f WHERE f.name = :name")
    Feature findByName(String name);
}
