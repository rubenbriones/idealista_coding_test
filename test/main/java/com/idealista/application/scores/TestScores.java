package com.idealista.application.scores;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

@RunWith(Parameterized.class)
public class TestScores {

    @Parameter(0)
    public AdVO ad;
    @Parameter(1)
    public List<PictureVO> pics;
    @Parameter(2)
    public int score;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { 
        	{new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.<Integer>emptyList(), 300, null, null, null), 
        		new ArrayList<PictureVO>(), 
        		15}, 
        	{new AdVO(1, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", Arrays.asList(4), 300, null, null, null), 
        		Arrays.asList(new PictureVO(4, "http://www.idealista.com/pictures/4", "HD")),
        		100},
        	{new AdVO(1, "GARAGE", "Garaje Céntrico", Arrays.asList(1, 4), null, null, null, null), 
        		Arrays.asList(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"), new PictureVO(4, "http://www.idealista.com/pictures/4", "HD")),
        		80},
        	{new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA", Arrays.asList(1), 300, 100, null, null), 
        		Arrays.asList(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD")), 
        		55},
        	{new AdVO(1, "FLAT", "Este piso es una ganga, compra, compra, COMPRA", Arrays.asList(1), null, null, null, null), 
        		Arrays.asList(new PictureVO(4, "http://www.idealista.com/pictures/4", "HD")), 
        		35}, 
        	{new AdVO(1, "CHALET", "Este piso", Collections.<Integer>emptyList(), 300, 100, null, null), 
        		new ArrayList<PictureVO>(),
        		0},
        };        		
        return Arrays.asList(data);
    }
    
	@Test
	public void testCalculateTotalScore() {
		assertEquals(score, (int)Scores.getInstance().calculateTotalScore(ad, pics));
	}

}
