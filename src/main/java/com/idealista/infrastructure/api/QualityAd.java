package com.idealista.infrastructure.api;

import java.util.Date;
import java.util.List;

import com.idealista.application.AdLoaded;

public class QualityAd extends AdLoaded {

	private Date irrelevantSince;

    public QualityAd(Integer id, String typology, String description, Integer houseSize, Integer gardenSize,
			List<String> pictureUrls, Integer score, Date irrelevantSince) {
		super(id, typology, description, houseSize, gardenSize, pictureUrls, score);
		this.irrelevantSince = irrelevantSince;
	}
    
    public Date getIrrelevantSince() {
        return irrelevantSince;
    }

    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }
}
