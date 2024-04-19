package com.moneylion.ta.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import com.moneylion.ta.model.UserFeature;

public interface UserFeatureRepository extends CrudRepository<UserFeature, Long> {
    @Query("SELECT uf.access FROM UserFeature uf WHERE uf.user.email = :email AND uf.feature.name = :featureName")
    Boolean canAccess(@Param("email") String email, @Param("featureName") String featureName);

    @Query("SELECT uf FROM UserFeature uf WHERE uf.user.email = ?1 AND uf.feature.name = ?2")
    UserFeature findByUserEmailAndFeatureName(String email, String featureName);
}
