package ru.konstantinpetrov.play_chords.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

public class Singers {

	private Integer id;
	

	private String name;
	

	private Byte gender;
	

	private String text;
	
	public Singers(Integer id, String name, Byte gender, String text) {
		this.id=id;
		this.name=name;
		this.gender=gender;
		this.text=text;
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
	
	public void setGender(Byte gender) {
		this.gender=gender;
	}
	
	public Byte getGender() {
		return this.gender;
	}
	
	public void setText(String text) {
		this.text=text;
	}
	
	public String getText() {
		return this.text;
	}
	
}
