package com.idealista.application.scores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.IAdsRepository;
import com.idealista.infrastructure.persistence.PictureVO;

public class ScoreRulePictures implements ScoreRule {
	
	private final static int NO_PICS = -10;
	private final static int PIC_HD = 20;
	private final static int PIC_SD = 10;
	
	@Override
	public int getScore(AdVO ad, List<PictureVO> pics) {
		if(ad.getPictures().size() == 0)
			return NO_PICS;
		else {
			int numHDpics = 0;
			for(PictureVO pic : pics)
				if(pic.getQuality().contentEquals("HD"))
					numHDpics++;
			
			return numHDpics*PIC_HD + (pics.size()-numHDpics)*PIC_SD;
		}
	}

}
