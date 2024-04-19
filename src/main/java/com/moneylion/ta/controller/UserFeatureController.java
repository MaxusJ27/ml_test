package com.moneylion.ta.controller;

// annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// models
import com.moneylion.ta.model.*;

// repositories
import com.moneylion.ta.repository.*;

import jakarta.persistence.EntityNotFoundException;

// logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// utils
import java.util.Map;

@RestController
public class UserFeatureController {
    private final UserFeatureRepository userFeatureRepository;
    private final UserRepository userRepository;
    private final FeatureRepository featureRepository;
    // log for debugging
    private static final Logger log = LoggerFactory.getLogger(UserFeatureController.class);

    @Autowired
    public UserFeatureController(UserRepository userRepository, FeatureRepository featureRepository,
            UserFeatureRepository userFeatureRepository) {
        this.userFeatureRepository = userFeatureRepository;
        this.userRepository = userRepository;
        this.featureRepository = featureRepository;
    }

    // endpoint to get user access based on feature
    @GetMapping("/feature")
    public ResponseEntity<Object> canAccess(@RequestParam String email, @RequestParam String featureName) {
        Boolean canAccess = this.userFeatureRepository.canAccess(email, featureName);

        if (canAccess != null) {
            return ResponseEntity.ok(Map.of("canAccess", canAccess));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // endpoint to enable or disable user access to feature
    @PostMapping("/feature")
    public ResponseEntity<Object> addFeatureAccess(@RequestBody UserFeatureRequest userFeatureRequest) {
        // get email and feature name
        String email = userFeatureRequest.getEmail();
        String featureName = userFeatureRequest.getFeatureName();
        // get userFeature by email and feature name
        UserFeature userFeature = this.userFeatureRepository.findByUserEmailAndFeatureName(email, featureName);
        // if null then return not modified status
        if (userFeature == null) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        userFeature.setAccess(userFeatureRequest.getEnable());
        // update the userFeature object
        UserFeature savedFeature = this.userFeatureRepository.save(userFeature);
        // handle exception
        if (savedFeature != null) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    // endpoint to add feature link by user email and feature name
    @PostMapping("/feature-add")
    public ResponseEntity<String> addUserFeature(@RequestBody UserFeatureRequest userFeatureRequest) {
        UserFeature savedFeature = null;
        try {
            User user = this.userRepository.findByEmail(userFeatureRequest.getEmail());
            if (user == null) {
                throw new EntityNotFoundException("User not found with email: " + userFeatureRequest.getEmail());
            }

            Feature feature = this.featureRepository.findByName(userFeatureRequest.getFeatureName());
            if (feature == null) {
                throw new EntityNotFoundException(
                        "Feature not found with name: " + userFeatureRequest.getFeatureName());
            }

            UserFeature userFeature = this.userFeatureRepository
                    .findByUserEmailAndFeatureName(userFeatureRequest.getEmail(), userFeatureRequest.getFeatureName());
            if (userFeature == null) {
                savedFeature = this.userFeatureRepository.save(new UserFeature(user, feature, false));
            }

            if (savedFeature != null) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                throw new Exception("Failed to save UserFeature");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
