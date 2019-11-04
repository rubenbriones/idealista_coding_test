package com.idealista.application.scores;

import java.util.ArrayList;
import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public class Scores {
	
	public static final int MIN_SCORE = 0;
	public static final int MAX_SCORE = 100;
	//Minimum score that an ad must have to be public.
	public final static int MIN_PUBLIC_SCORE = 40;
	

	private static final Scores SINGLE_INSTANCE = new Scores();
	
	//List with all the ScoreRule we want to apply when calculating the total score of an ad.
	private List<ScoreRule> scoresRules;
	
	private Scores() {
		scoresRules = new ArrayList<ScoreRule>();
		
		//By default we add the next ScoreRules
		scoresRules.add(new ScoreRulePictures());
		scoresRules.add(new ScoreRuleDescription());
		scoresRules.add(new ScoreRuleKeywords());
		scoresRules.add(new ScoreRuleFullAd());		
	}
	
	public static Scores getInstance() {
		return SINGLE_INSTANCE;
	}
	
	public List<ScoreRule> getScoresRules() {
		return scoresRules;
	}

	public void setScoresRules(List<ScoreRule> scoresRules) {
		this.scoresRules = scoresRules;
	}
	
	public void addScoreRule(ScoreRule rule) {
		scoresRules.add(rule);
	}
	
	public void removeScoreRule(ScoreRule rule) {
		scoresRules.remove(rule);
	}
	
	public Integer calculateTotalScore(AdVO ad, List<PictureVO> pics) {
		int totalScore = 0;
		for(ScoreRule rule : scoresRules) {
			totalScore += rule.getScore(ad, pics);
		}
		return new Integer(Math.max(MIN_SCORE, Math.min(MAX_SCORE, totalScore)));
	}
}
