package ru.konstantinpetrov.play_chords.entity;

import lombok.*;



import com.fasterxml.jackson.annotation.JsonProperty;


@RequiredArgsConstructor

public class Users {

    private Integer id;
	
	
    private String login;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
	
    private String mail;

	public Users(Integer id, String login, String password, String mail) {
		this.id=id;
		this.login=login;
		this.password=password;
		this.mail=mail;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return(password);
	}
	
	public Integer getId() {
		return(id);
	}
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public String getLogin() {
		return(login);
	}
	
	public void setMail(String mail) {
		this.mail=mail;
	}
	
	public String getMail() {
		return(this.mail);
	}

	
}
