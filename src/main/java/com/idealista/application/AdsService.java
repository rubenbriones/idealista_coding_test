package com.idealista.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealista.application.scores.Scores;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.IAdsRepository;
import com.idealista.infrastructure.persistence.PictureVO;

@Service
public class AdsService implements IAdsService {
	
	@Autowired
    private IAdsRepository repository;
	
	private List<AdLoaded> adsLoaded;
	
	@Override
	public void calculateScores() {
		List<AdVO> ads = repository.findAllAds();
		Map<Integer, List<PictureVO>> mapPics = repository.findPictures(ads);
		
		for(AdVO ad : ads) {
			ad.setScore(Scores.getInstance().calculateTotalScore(ad, mapPics.get(ad.getId())));
			if(ad.getScore() >= 40) {
				ad.setIrrelevantSince(null);
			}
			else{
				if(ad.getIrrelevantSince() == null)
					ad.setIrrelevantSince(new Date());
			}
		}
		
		//Here we should implement persistance, but is not a requirement for the coding test.
		
		this.adsLoaded = convertAdsVOtoAdsLoaded(ads, mapPics);
	}
	
	private List<AdLoaded> convertAdsVOtoAdsLoaded(List<AdVO> adsVO, Map<Integer, List<PictureVO>> mapPics){
		List<AdLoaded> adsConverted = new ArrayList<AdLoaded>(adsVO.size());
		for (AdVO adVO : adsVO) {
			List<PictureVO> pics = mapPics.get(adVO.getId());
			List<String> picsUrls = new ArrayList<String>(pics.size());
			for(PictureVO p : pics) picsUrls.add(p.getUrl());
			
			AdLoaded adConverted = null;
			if(adVO.getScore() >= Scores.MIN_PUBLIC_SCORE) {
				adConverted = new PublicAd(adVO.getId(), adVO.getTypology(), adVO.getDescription(), adVO.getHouseSize(), adVO.getGardenSize(), picsUrls, adVO.getScore());
			}
			else {
				adConverted = new QualityAd(adVO.getId(), adVO.getTypology(), adVO.getDescription(), adVO.getHouseSize(), adVO.getGardenSize(), picsUrls, adVO.getScore(), adVO.getIrrelevantSince());
			}
			adsConverted.add(adConverted);
		}
		return adsConverted;
	}

	@Override
	public List<QualityAd> getQualityAds() {
		if(this.adsLoaded == null)
			calculateScores();
		
		List<QualityAd> qualityAds = new ArrayList<QualityAd>();
		for(int i = 0; i < adsLoaded.size(); i++) {
			if(adsLoaded.get(i) instanceof QualityAd)
				qualityAds.add((QualityAd)adsLoaded.get(i));
		}
		
		return qualityAds;
	}

	@Override
	public List<PublicAd> getPublicAds() {
		if(this.adsLoaded == null)
			calculateScores();
		
		List<PublicAd> publicAds = new ArrayList<PublicAd>();
		for(int i = 0; i < adsLoaded.size(); i++) {
			if(adsLoaded.get(i) instanceof PublicAd)
				publicAds.add((PublicAd)adsLoaded.get(i));
		}
		
		return publicAds;
	}

}
