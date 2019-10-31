package com.idealista.infrastructure.persistence;

import java.util.List;

public interface IAdsRepository {
	public List<AdVO> findAllAds();
	public List<PictureVO> getPicturesById(List<Integer> ids);
}
