package com.idealista.application.scores;

import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public class ScoreRuleDescription implements ScoreRule {

	private final static int HAVE_DESC = 5;
	
	private final static int FLAT_GT_50 = 30;
	private final static int FLAT_BW_20_50 = 10;
	
	private final static int CHALET_GT_50 = 20;	
	
	@Override
	public int getScore(AdVO ad, List<PictureVO> pics) {
		int score = 0;
		
		if(!ad.getDescription().equals("")) {
			score += HAVE_DESC;
			
			int descLength = ad.getDescription().length();
			
			switch(ad.getTypology()) {
				case "FLAT":
					if(descLength >= 50)
						score += FLAT_GT_50;
					else if (descLength >= 20)
						score += FLAT_BW_20_50;
					break;
					
				case "CHALET":
					if(descLength > 50)
						score += CHALET_GT_50;
					break;
			}
		}
		
		return score;
	}

}
