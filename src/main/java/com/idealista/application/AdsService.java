package com.idealista.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		for(AdVO ad : ads) {
			ad.setScore(calculateScore(ad));
			if(ad.getScore() >= 40) {
				ad.setIrrelevantSince(null);
			}
			else{
				if(ad.getIrrelevantSince() == null)
					ad.setIrrelevantSince(new Date());
			}
		}
		
		//Here we should implement persistance, but is not a requirement for the coding test.
		
		this.adsLoaded = convertAdsVOtoAdsLoaded(ads);
	}
	
	private List<AdLoaded> convertAdsVOtoAdsLoaded(List<AdVO> adsVO){
		List<AdLoaded> adsConverted = new ArrayList<AdLoaded>(adsVO.size());
		for (AdVO adVO : adsVO) {
			List<PictureVO> pics = repository.getPicturesById(adVO.getPictures());
			List<String> picsUrls = new ArrayList<String>(pics.size());
			for(PictureVO p : pics) picsUrls.add(p.getUrl());
			
			AdLoaded adConverted = null;
			if(adVO.getScore() >= 40) {
				adConverted = new PublicAd(adVO.getId(), adVO.getTypology(), adVO.getDescription(), adVO.getHouseSize(), adVO.getGardenSize(), picsUrls, adVO.getScore());
			}
			else {
				adConverted = new QualityAd(adVO.getId(), adVO.getTypology(), adVO.getDescription(), adVO.getHouseSize(), adVO.getGardenSize(), picsUrls, adVO.getScore(), adVO.getIrrelevantSince());
			}
			adsConverted.add(adConverted);
		}
		return adsConverted;
	}
	
	private Integer calculateScore(AdVO ad) {
		int score = 0;
		
		if(ad.getPictures().size() == 0) score -= 10;
		else {
			List<PictureVO> pics = repository.getPicturesById(ad.getPictures());
			int numHDpics = 0;
			for(PictureVO pic : pics) if(pic.getQuality().contentEquals("HD")) numHDpics++;
			score += numHDpics*20 + (pics.size()-numHDpics)*10;
		}
		
		if(!ad.getDescription().equals("")) {
			score += 5;
			
			int descLength = ad.getDescription().length();
			if(ad.getTypology().equals("FLAT")) {
				if(descLength >= 50) score += 30;
				else if (descLength >= 20) score += 10;	
			}
			else if(ad.getTypology().equals("CHALET")) {
				if(descLength > 50) score += 20;
			}
		}
		
		String[] keyWords = {"luminoso", "nuevo", "céntrico", "reformado", "ático"};
		String desc = ad.getDescription().toLowerCase();
		List<String> words = Arrays.asList(desc.split(" "));
		for(String kw : keyWords) {
			if(words.contains(kw)) score += 5;
		}
		
		if(ad.getPictures().size() > 0) {
			boolean fullAd = false;
			switch(ad.getTypology()) {
				case "GARAGE": 
					fullAd = true; 
					break;

				case "CHALET": 
					if(ad.getGardenSize() != null)
						fullAd = true;
					else
						break;
				case "FLAT":
					if(ad.getDescription().length() > 0 && ad.getHouseSize() != null)
						fullAd = true;
					else
						fullAd = false;
					break;
			}
			if(fullAd) score += 40;
		}
		
		return new Integer(Math.max(0, Math.min(100, score)));
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
