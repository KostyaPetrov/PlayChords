package ru.konstantinpetrov.play_chords.entity;

import java.io.Serializable;



import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

public class TagsSongs implements Serializable {
	
	private Integer tagsId;
	
	
	private Integer songsId;
	
	public TagsSongs(Integer tagsId, Integer songsId) {
		this.tagsId=tagsId;
		this.songsId=songsId;
	}
	
	public void setTagsId(Integer tagsId) {
		this.tagsId=tagsId;
	}
	
	public Integer getTagsId() {
		return this.tagsId;
	}
	
	public void setSongsId(Integer songsId) {
		this.songsId=songsId;
	}
	
	public Integer getSongsId() {
		return this.songsId;
	}
}
