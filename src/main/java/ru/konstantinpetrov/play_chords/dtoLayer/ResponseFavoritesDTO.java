package ru.konstantinpetrov.play_chords.dtoLayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ru.konstantinpetrov.play_chords.entity.Favorites;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseFavoritesDTO {
	private List<Favorites> fav;
	
	public ResponseFavoritesDTO(List<Favorites> fav) {
    	this.fav=fav;
    }

	public List<Favorites> getFavorites() {
		return fav;
	}

	public void setFavorites(List<Favorites> fav) {
		this.fav = fav;
	}
}
