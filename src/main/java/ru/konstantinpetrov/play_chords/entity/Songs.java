package ru.konstantinpetrov.play_chords.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

public class Songs {
	
	private Integer id;
	
	
	private String gener;
	

	private String name;
	
	
	private String musician;
	

	private String text;
	

	private Integer levelId;
	
	private Integer visitorsCount;
	
	
	public Songs(Integer id, String gener, String name, String musician,String text,Integer levelId, Integer visitorsCount) {
		this.id=id;
		this.gener=gener;
		this.name=name;
		this.musician=musician;
		this.text=text;
		this.levelId=levelId;
		this.visitorsCount=visitorsCount;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setGener(String gener) {
		this.gener=gener;
	}
	
	public String getGener() {
		return this.gener;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setMusician(String musician) {
		this.musician=musician;
	}
	
	public String getMusician() {
		return this.musician;
	}
	
	public void setText(String text) {
		this.text=text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setLevelId(Integer levelId) {
		this.levelId=levelId;
	}
	
	public Integer getLevelId() {
		return this.levelId;
	}
	
	public void setVisitorsCount(Integer visitorsCount) {
		this.visitorsCount=visitorsCount;
	}
	
	public Integer getVisitorsCount() {
		return this.visitorsCount;
	}
}
