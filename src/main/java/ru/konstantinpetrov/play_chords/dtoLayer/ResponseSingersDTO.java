package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Singers;
import ru.konstantinpetrov.play_chords.entity.SongsSingers;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseSingersDTO {
	private List<Singers> singers;
	
	public ResponseSingersDTO(List<Singers> singers) {
    	this.singers=singers;
    }

	public List<Singers> getSingers() {
		return singers;
	}

	public void setSingers(List<Singers> singers) {
		this.singers = singers;
	}
}
