package com.pecasDelivery.AutoPecas_Delivery_API.security.details;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pecasDelivery.AutoPecas_Delivery_API.model.Usuario;

public class UserDetailsImpl implements UserDetails{

	private Usuario usuario;
	
	public UserDetailsImpl(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return usuario.getRoles()
						.stream()
						.map(role -> new SimpleGrantedAuthority(role.getName().name()))
						.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getNomeUsuario();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
