package ru.konstantinpetrov.play_chords.entity;

import java.io.Serializable;



import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

public class SongsSingers implements Serializable {
	
	private Integer songsId;
	
	
	private Integer singersId;
	
	
	private String linkToVideo;
	
	public SongsSingers(Integer songsId, Integer singersId, String linkToVideo) {
		this.songsId=songsId;
		this.singersId=singersId;
		this.linkToVideo=linkToVideo;
	}
	
	public void setSongsId(Integer songsId) {
		this.songsId=songsId;
	}
	
	public Integer getSongsId() {
		return this.songsId;
	}
	
	public void setSingersId(Integer singersId) {
		this.singersId=singersId;
	}
	
	public Integer getSingersId() {
		return this.singersId;
	}
	
	public void setLinkToVideo(String linkToVideo) {
		this.linkToVideo=linkToVideo;
	}
	
	public String getLinkToVideo() {
		return this.linkToVideo;
	}
}
