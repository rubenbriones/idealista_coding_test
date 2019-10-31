package com.idealista.infrastructure.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idealista.application.IAdsService;

@RestController
public class AdsController {

	@Autowired
	private IAdsService adsService;
	
	@GetMapping(value = "/")
	public String home() {
		return "Hello World!";
	}
	
	@GetMapping(value = "/quality-ads")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        return ResponseEntity.ok(adsService.getQualityAds());
    }

	@GetMapping(value = "/public-ads")
    public ResponseEntity<List<PublicAd>> publicListing() {
        return ResponseEntity.ok(adsService.getPublicAds());
    }

	@PutMapping(value = "/calculate-scores")
    public ResponseEntity<Void> calculateScores() {
        adsService.calculateScores();
        return ResponseEntity.ok().build();
    }
}
