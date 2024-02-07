package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Singers;
import ru.konstantinpetrov.play_chords.entity.Tags;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseTagsDTO {
	private List<Tags> tag;
	
	public ResponseTagsDTO(List<Tags> tag) {
    	this.tag=tag;
    }

	public List<Tags> getTags() {
		return tag;
	}

	public void setTags(List<Tags> tag) {
		this.tag = tag;
	}
}
