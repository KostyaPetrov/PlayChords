package ru.konstantinpetrov.play_chords.entity;

import java.io.Serializable;



import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

public class FoldersSongs implements Serializable {
	
	private Integer foldersId;
	
	
	private Integer songsId;
	
	public FoldersSongs(Integer foldersId, Integer songsId) {
		this.foldersId=foldersId;
		this.songsId=songsId;
	}
	
	public void setFoldersId(Integer foldersId) {
		this.foldersId=foldersId;
	}
	
	public Integer getFoldersId() {
		return this.foldersId;
	}
	
	public void setSongsId(Integer songsId) {
		this.songsId=songsId;
	}
	
	public Integer getSongsId() {
		return this.songsId;
	}
}
