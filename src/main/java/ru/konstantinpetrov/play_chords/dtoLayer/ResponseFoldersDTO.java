package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Folders;
import ru.konstantinpetrov.play_chords.entity.Tags;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseFoldersDTO {
	private List<Folders> folder;
	
	public ResponseFoldersDTO(List<Folders> folder) {
    	this.folder=folder;
    }

	public List<Folders> getFolders() {
		return folder;
	}

	public void setFolders(List<Folders> folder) {
		this.folder = folder;
	}
}
