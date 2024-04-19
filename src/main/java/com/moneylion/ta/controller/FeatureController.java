package com.moneylion.ta.controller;
// annotations
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
// model
import com.moneylion.ta.model.Feature;
// repository
import com.moneylion.ta.repository.FeatureRepository;

@RestController
public class FeatureController {
    private final FeatureRepository featureRepository;

    public FeatureController(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }
    
    // endpoint to retrieve all the features
    @GetMapping("/features")
    public Iterable<Feature> findAllFeatures() {
        return this.featureRepository.findAll();
    }
    // endpoint to post features
    @PostMapping("/features")
    public Feature addFeature(@RequestBody Feature feature) {
        return this.featureRepository.save(feature);
    }
}
