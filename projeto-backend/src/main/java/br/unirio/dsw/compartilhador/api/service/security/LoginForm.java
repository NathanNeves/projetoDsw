package br.unirio.dsw.compartilhador.api.service.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a login form
 * 
 * @author User
 */
public class LoginForm
{
	private @Getter @Setter String email;
	private @Getter @Setter String senha;
	public String getEmail() {
		return this.email;
	}
	public String getSenha() {
		return this.senha;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}