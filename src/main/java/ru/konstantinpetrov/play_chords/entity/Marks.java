package ru.konstantinpetrov.play_chords.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

public class Marks {

	private Integer id;
	

	private String reaction;
	

	private Integer songsId;
	
	public Marks(Integer id, String reaction, Integer songsId) {
		this.id=id;
		this.reaction=reaction;
		this.songsId=songsId;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setReaction(String reaction) {
		this.reaction=reaction;
	}
	
	public String getReaction() {
		return this.reaction;
	}
	
	public void setSongsId(Integer songsId) {
		this.songsId=songsId;
	}
	
	public Integer getSongsId() {
		return this.songsId;
	}
}
