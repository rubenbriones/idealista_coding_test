package com.idealista.application;

import java.util.List;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;

public interface IAdsService {
	public void calculateScores();
	public List<QualityAd> getQualityAds();
	public List<PublicAd> getPublicAds();
}
