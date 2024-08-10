package com.pecasDelivery.AutoPecas_Delivery_API.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pecasDelivery.AutoPecas_Delivery_API.model.Usuario;
import com.pecasDelivery.AutoPecas_Delivery_API.repositories.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	/*
	 * Metodo chamado automaticamente pelo spring no processo de autenticação
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		return new UserDetailsImpl(usuario);
	}

}
