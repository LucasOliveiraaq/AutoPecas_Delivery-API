package com.pecasDelivery.AutoPecas_Delivery_API.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pecasDelivery.AutoPecas_Delivery_API.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByLogin(String login);
}
