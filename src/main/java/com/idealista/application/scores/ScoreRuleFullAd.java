package com.idealista.application.scores;

import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public class ScoreRuleFullAd implements ScoreRule {

	private final static int FULL_AD = 40;
	
	@Override
	public int getScore(AdVO ad, List<PictureVO> pics) {
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
			if(fullAd)
				return FULL_AD;
		}
		
		return 0;		
	}	

}
