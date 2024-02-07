package ru.konstantinpetrov.play_chords.dtoLayer;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEnterDTO {
    private Integer id;
    
    public ResponseEnterDTO(Integer id) {
    	this.id=id;
    }
}
