package com.idealista.application;

import java.util.List;

public abstract class AdLoaded {

	private Integer id;
    private String typology;
    private String description;
    private Integer houseSize;
    private Integer gardenSize;
    private List<String> pictureUrls;
	private Integer score;

	public AdLoaded(Integer id, String typology, String description, Integer houseSize, Integer gardenSize,
			List<String> pictureUrls, Integer score) {
		this.id = id;
		this.typology = typology;
		this.description = description;
		this.houseSize = houseSize;
		this.gardenSize = gardenSize;
		this.pictureUrls = pictureUrls;
		this.score = score;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }
    
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
