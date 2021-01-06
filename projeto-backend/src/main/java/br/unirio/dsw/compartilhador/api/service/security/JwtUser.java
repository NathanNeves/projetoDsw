package br.unirio.dsw.compartilhador.api.service.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.unirio.dsw.compartilhador.api.model.Usuario;
import br.unirio.dsw.compartilhador.api.repository.UsuarioRepository;
import br.unirio.dsw.compartilhador.api.utils.spring.ControllerResponse;
import lombok.Getter;

/**
 * Classe que representa um usuário no JWT
 * 
 * @author User
 */
public class JwtUser implements UserDetails
{
	private static final long serialVersionUID = -268046329085485932L;

	private @Getter Long id;
	private String username;
	private String password;
	private @Getter Date lastLoginDate;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * Inicializa o usuário com as suas informações
	 */
	public JwtUser(Long id, String username, String password, Date lastLoginDate, Collection<? extends GrantedAuthority> authorities)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastLoginDate = lastLoginDate;
		this.authorities = authorities;
	}

	/**
	 * Retorna o login do usuário, que no caso é o seu e-mail
	 */
	@Override
	public String getUsername()
	{
		return username;
	}

	/**
	 * Verifica se a conta do usuário é válida
	 */
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	/**
	 * Verifica se as credenciais do usuário são válidas
	 */
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/**
	 * Verifica se a conta do usuário está bloqueada
	 */
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	public static Usuario isAuthenticated(UsuarioRepository usuarioRepositorio){
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (username == null)
			//return ControllerResponse.fail("nome", "Não há um usuário logado no sistema.");
			return null;
        Usuario usuario = usuarioRepositorio.findByEmail(username);
		if (usuario == null)
			//return ControllerResponse.fail("nome", "Não foi possível recuperar os dados do usuário a partir das credenciais.");
			return null;
		return usuario;
	}

	/**
	 * Retorna a senha do usuário
	 */
	@Override
	public String getPassword()
	{
		return password;
	}

	/**
	 * Retorna os papéis cumpridos pelo usuário
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return authorities;
	}

	/**
	 * Verifica se o usuário está ativo
	 */
	@Override
	public boolean isEnabled()
	{
		return true;
	}
}