package ru.konstantinpetrov.play_chords.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class Levels {
	
	
	private Integer id;
	
	
	private String levelName;
	
	
	private String info;
	
	public Levels(Integer id, String levelName, String info) {
		this.id=id;
		this.levelName=levelName;
		this.info=info;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setLevelName(String levelName) {
		this.levelName=levelName;
	}
	
	public String getLevelName() {
		return this.levelName;
	}
	
	public void setInfo(String info) {
		this.info=info;
	}
	
	public String getInfo() {
		return this.info;
	}
}
