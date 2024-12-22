package ru.konstantinpetrov.play_chords.controller;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.konstantinpetrov.play_chords.DB.DBManager;
import ru.konstantinpetrov.play_chords.dtoLayer.ResponseEnterDTO;
import ru.konstantinpetrov.play_chords.entity.Users;


@Slf4j
@RestController
public class UserController {
	private DBManager dbManager;
	
	@PostMapping(path="/addUser/{user}")
	public ResponseEntity<ResponseEnterDTO> addUser(@PathVariable Users user) {
		try {
			log.info("User getted", user);
			this.dbManager.addUser(user);
			return new ResponseEntity<>(new ResponseEnterDTO(user.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving user", e);
            return new ResponseEntity<>(new ResponseEnterDTO(user.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
}
