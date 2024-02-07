package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Comments;
import ru.konstantinpetrov.play_chords.entity.Marks;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseMarksDTO {
	private List<Marks> mark;
	
	public ResponseMarksDTO(List<Marks> mark) {
    	this.mark=mark;
    }

	public List<Marks> getMark() {
		return mark;
	}

	public void setMark(List<Marks> mark) {
		this.mark = mark;
	}
	
	
	
}
