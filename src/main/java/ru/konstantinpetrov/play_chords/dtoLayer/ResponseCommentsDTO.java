package ru.konstantinpetrov.play_chords.dtoLayer;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.konstantinpetrov.play_chords.entity.Comments;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCommentsDTO {
	private List<Comments> comment;
	
	public ResponseCommentsDTO(List<Comments> comment) {
    	this.comment=comment;
    }

	public List<Comments> getComment() {
		return comment;
	}

	public void setComment(List<Comments> comment) {
		this.comment = comment;
	}
	
}
