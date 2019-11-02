package edu.northeastern.cs5200.entities;

import javax.persistence.Entity;

@Entity
public class YouTubeWidget extends Widget {
	private String youTubeId;

	public YouTubeWidget() {
		super();
	}

	public String getYouTubeId() {
		return youTubeId;
	}

	public void setYouTubeId(String youTubeId) {
		this.youTubeId = youTubeId;
	}
	
}
