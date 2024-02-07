package ru.konstantinpetrov.play_chords.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Folders {
	
	private Integer id;
	
	
	private String name;
	
	
	private Integer userId;
	
	public Folders(Integer id, String name, Integer userId) {
		this.id=id;
		this.name=name;
		this.userId=userId;
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
	
	public void setUserId(Integer userId) {
		this.userId=userId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
}
