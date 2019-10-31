package com.idealista.infrastructure.api;

import java.util.List;

import com.idealista.application.AdLoaded;

public class PublicAd extends AdLoaded {

	public PublicAd(Integer id, String typology, String description, Integer houseSize, Integer gardenSize,
			List<String> pictureUrls, Integer score) {
		super(id, typology, description, houseSize, gardenSize, pictureUrls, score);
	}

}
