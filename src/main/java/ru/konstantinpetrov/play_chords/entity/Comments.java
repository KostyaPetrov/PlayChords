package ru.konstantinpetrov.play_chords.entity;

import java.util.Date;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Comments {
	
	private Integer id;
	
	
	private String text;
	
	
	private Date date;
	
	
	private Integer songsId;
	
	
	private Integer usersId;
	
	public Comments(Integer id, String text, Date date, Integer songsId, Integer usersId) {
		this.id=id;
		this.text=text;
		this.date=date;
		this.songsId=songsId;
		this.usersId=usersId;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setText(String text) {
		this.text=text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setDate(Date date) {
		this.date=date;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setSongsId(Integer songsId) {
		this.songsId=songsId;
	}
	
	public Integer getSongsId() {
		return this.songsId;
	}
	
	
	public void setUsersId(Integer usersId) {
		this.usersId=usersId;
	}
	
	public Integer getUsersId() {
		return this.usersId;
	}
}
