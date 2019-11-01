package com.idealista.application.scores;

import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public interface ScoreRule {
	public int getScore(AdVO ad, List<PictureVO> pics);
}
