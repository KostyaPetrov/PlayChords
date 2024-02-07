package ru.konstantinpetrov.play_chords.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class Tags {
	
	private Integer id;
	
	
	private String name;
	
	public Tags(Integer id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
}
