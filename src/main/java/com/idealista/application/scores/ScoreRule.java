package com.idealista.application.scores;

import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

/**
 * Interface that must implement any "rule" we want to apply for calculating the total score of an ad.
 */
public interface ScoreRule {
	public int getScore(AdVO ad, List<PictureVO> pics);
}
