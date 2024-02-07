package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.FoldersSongs;
import ru.konstantinpetrov.play_chords.entity.TagsSongs;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseTagsSongsDTO {
	private List<TagsSongs> tagsSongs;
	
	public ResponseTagsSongsDTO(List<TagsSongs> tagsSongs) {
    	this.tagsSongs=tagsSongs;
    }

	public List<TagsSongs> getTagsSongs() {
		return tagsSongs;
	}

	public void setTagsSongs(List<TagsSongs> tagsSongs) {
		this.tagsSongs = tagsSongs;
	}
}
