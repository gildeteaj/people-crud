package com.myapi.peoplews.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Usuario")
public class Usuario {

	@Transient
    public static final String SEQUENCE_NAME = "usuario_sequence";
	
	@Id
	private long id;
	
	@NotBlank
    @Size(max=20)
    @Indexed(unique=true)
	private String login;
	
	
	@NotBlank
    @Size(max=10)    
	private String senha;
	
	public Usuario() {
		
	}
	
	
	
	public Usuario(@NotBlank @Size(max = 20) String login, @NotBlank @Size(max = 10) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + "]";
	}



	
}
