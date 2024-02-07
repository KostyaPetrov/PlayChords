package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Songs;
import ru.konstantinpetrov.play_chords.entity.SongsSingers;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseSongsDTO {
	private List<Songs> songs;
	
	public ResponseSongsDTO(List<Songs> songs) {
    	this.songs=songs;
    }

	public List<Songs> getSongs() {
		return songs;
	}

	public void setSongs(List<Songs> songs) {
		this.songs = songs;
	}
}
