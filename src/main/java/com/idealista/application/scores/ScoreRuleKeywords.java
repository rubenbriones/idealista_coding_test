package com.idealista.application.scores;

import java.util.Arrays;
import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public class ScoreRuleKeywords implements ScoreRule {

	private final static String[] KEY_WORDS = {"luminoso", "nuevo", "céntrico", "reformado", "ático"};
	private final static int POINTS = 5;
	
	@Override
	public int getScore(AdVO ad, List<PictureVO> pics) {
		int score = 0;
		String desc = ad.getDescription().toLowerCase();
		List<String> words = Arrays.asList(desc.split(" "));
		for(String kw : KEY_WORDS) {
			if(words.contains(kw))
				score += POINTS;
		}
		return score;
	}

}
