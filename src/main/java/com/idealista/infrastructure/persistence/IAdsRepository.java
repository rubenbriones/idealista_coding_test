package com.idealista.infrastructure.persistence;

import java.util.List;
import java.util.Map;

public interface IAdsRepository {
	
	/**
	 * @return list with all the ads in the repository.
	 */
	public List<AdVO> findAllAds();
	
	/**
	 * @return map where the keys are the IDs of the adds passed as param,
	 * 		   and the values are lists with the pictures of that ad.
	 */
	public Map<Integer, List<PictureVO>> findPictures(List<AdVO> ads);
}
