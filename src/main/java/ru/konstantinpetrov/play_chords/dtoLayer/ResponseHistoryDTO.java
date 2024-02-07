package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.History;
import ru.konstantinpetrov.play_chords.entity.Marks;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseHistoryDTO {
	private List<History> hist;
	
	public ResponseHistoryDTO(List<History> hist) {
    	this.hist=hist;
    }

	public List<History> getHistory() {
		return hist;
	}

	public void setHistory(List<History> hist) {
		this.hist = hist;
	}
}
