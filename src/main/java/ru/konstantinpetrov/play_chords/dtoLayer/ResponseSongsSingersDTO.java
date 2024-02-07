package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.SongsSingers;
import ru.konstantinpetrov.play_chords.entity.TagsSongs;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseSongsSingersDTO {
	private List<SongsSingers> songsSingers;
	
	public ResponseSongsSingersDTO(List<SongsSingers> songsSingers) {
    	this.songsSingers=songsSingers;
    }

	public List<SongsSingers> getSongsSingers() {
		return songsSingers;
	}

	public void setSongsSingers(List<SongsSingers> songsSingers) {
		this.songsSingers = songsSingers;
	}
}
