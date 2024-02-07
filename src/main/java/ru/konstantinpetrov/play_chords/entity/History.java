package ru.konstantinpetrov.play_chords.entity;

import java.io.Serializable;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

public class History implements Serializable {

	private Integer usersId;
	
	
	private Integer songsId;
	
	public History(Integer usersId, Integer songsId) {
		this.usersId=usersId;
		this.songsId=songsId;
	}
	
	public void setUsersId(Integer usersId) {
		this.usersId=usersId;
	}
	
	public Integer getUsersId() {
		return this.usersId;
	}
	
	public void setSongsId(Integer songsId) {
		this.songsId=songsId;
	}
	
	public Integer getSongsId() {
		return this.songsId;
	}
	
}
