package com.moneylion.ta.service;

// service
import org.springframework.stereotype.Service;
// annotation
import org.springframework.beans.factory.annotation.Autowired;
// repository
import com.moneylion.ta.repository.*;
// model
import com.moneylion.ta.model.*;
// logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// utils
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// service class to seed data into database
@Service
public class SeedingService {
    private final UserRepository userRepository;
    private final FeatureRepository featureRepository;
    private final UserFeatureRepository userFeatureAccessRepository;
    // for debugging
    private static final Logger log = LoggerFactory.getLogger(SeedingService.class);
    // constructor injection
    @Autowired
    public SeedingService(UserRepository userRepository, FeatureRepository featureRepository,
            UserFeatureRepository userFeatureAccessRepository) {
        this.userRepository = userRepository;
        this.featureRepository = featureRepository;
        this.userFeatureAccessRepository = userFeatureAccessRepository;
    }
    // class to seed users
    private CompletableFuture<List<User>> seedUsers() {
        return CompletableFuture.supplyAsync(() -> {
            List<User> users = Arrays.asList(
                    new User("Maxus", "maxusj27@gmail.com"),
                    new User("John", "johndoe@gmail.com"),
                    new User("King", "kingkong@gmail.com"),
                    new User("Lemon", "lemonman@gmail.com"),
                    new User("Fish", "fishboy@gmail.com")

            );
            this.userRepository.saveAll(users);
            return users;
        });
    }
    // class to seed features
    private CompletableFuture<List<Feature>> seedFeatures() {
        return CompletableFuture.supplyAsync(() -> {
            List<Feature> features = Arrays.asList(
                    new Feature("readFile", "Enable user to read file from storage."),
                    new Feature("writeFile", "Enable user to write file into storage."),
                    new Feature("deleteFile", "Enable user to delete file from storage."),
                    new Feature("uploadFile", "Enable user to upload file into storage as bulk."),
                    new Feature("downloadFile", "Enable user to download all the files."));
            featureRepository.saveAll(features);
            return features;
        });
    }
    // class to seed userFeatures, initialized with false access to all users
    private void seedUserFeatures(List<User> users, List<Feature> features) {
        // Seed user features using the provided users and features
        for (User user : users) {
            for (Feature feature : features) {
                userFeatureAccessRepository.save(new UserFeature(user, feature, false)); // Example of saving user
                                                                                         // features
            }
        }
    }
    // seed data into database
    public void seedData() {
        CompletableFuture<List<User>> usersFuture = seedUsers();
        CompletableFuture<List<Feature>> featuresFuture = seedFeatures();

        CompletableFuture.allOf(usersFuture, featuresFuture)
                .thenAccept(ignored -> seedUserFeatures(usersFuture.join(), featuresFuture.join()))
                .join();

        // log.info("All user features found");
        // this.userFeatureAccessRepository.findAll().forEach(userFeature -> {
        //     log.info(userFeature.toString());
        // });
    }
}
