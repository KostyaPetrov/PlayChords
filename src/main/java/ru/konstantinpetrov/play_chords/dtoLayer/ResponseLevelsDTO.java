package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Levels;
import ru.konstantinpetrov.play_chords.entity.Singers;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseLevelsDTO {
	private List<Levels> levels;
	
	public ResponseLevelsDTO(List<Levels> levels) {
    	this.levels=levels;
    }

	public List<Levels> getLevels() {
		return levels;
	}

	public void setLevels(List<Levels> levels) {
		this.levels = levels;
	}
}
