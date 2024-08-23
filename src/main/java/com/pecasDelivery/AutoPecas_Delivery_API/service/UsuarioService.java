package com.pecasDelivery.AutoPecas_Delivery_API.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pecasDelivery.AutoPecas_Delivery_API.dto.RegisterDTO;
import com.pecasDelivery.AutoPecas_Delivery_API.model.Role;
import com.pecasDelivery.AutoPecas_Delivery_API.model.RoleName;
import com.pecasDelivery.AutoPecas_Delivery_API.model.Usuario;
import com.pecasDelivery.AutoPecas_Delivery_API.repositories.RoleRepository;
import com.pecasDelivery.AutoPecas_Delivery_API.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	public Usuario registerNewUser(RegisterDTO registerDTO) throws Exception {
		if (usuarioRepository.findByLogin(registerDTO.login()).isPresent()
				|| usuarioRepository.findByEmail(registerDTO.email()).isPresent()) {
			throw new Exception("Login ou e-mail já estão em uso");
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.senha());
		Usuario newUsuario = new Usuario(registerDTO.login(), encryptedPassword, registerDTO.email());

		// Lista para armazenar as roles associadas ao usuário
		List<Role> userRoles = new ArrayList<>();

		for (String roleName : registerDTO.roles()) {
			try {
				RoleName enumRoleName = RoleName.valueOf(roleName);
				Role role = roleRepository.findByName(enumRoleName)
						.orElseThrow(() -> new Exception("Role não encontrada: " + roleName));
				userRoles.add(role);
			} catch (IllegalArgumentException e) {
				throw new Exception("Role inválida: " + roleName);
			}
		}

		newUsuario.setRoles(userRoles);

		return usuarioRepository.save(newUsuario);
	}
}
