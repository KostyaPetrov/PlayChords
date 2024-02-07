package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Favorites;
import ru.konstantinpetrov.play_chords.entity.FoldersSongs;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseFoldersSongsDTO {
	private List<FoldersSongs> folder;
	
	public ResponseFoldersSongsDTO(List<FoldersSongs> folder) {
    	this.folder=folder;
    }

	public List<FoldersSongs> getFoldersSongs() {
		return folder;
	}

	public void setFoldersSongs(List<FoldersSongs> folder) {
		this.folder = folder;
	}
}
