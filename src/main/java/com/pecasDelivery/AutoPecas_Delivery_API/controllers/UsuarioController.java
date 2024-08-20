package com.pecasDelivery.AutoPecas_Delivery_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pecasDelivery.AutoPecas_Delivery_API.dto.AuthenticationDTO;
import com.pecasDelivery.AutoPecas_Delivery_API.dto.RegisterDTO;
import com.pecasDelivery.AutoPecas_Delivery_API.model.Usuario;
import com.pecasDelivery.AutoPecas_Delivery_API.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario/login")
@RequiredArgsConstructor
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;

	@GetMapping("/test")
	public String test() {
		return "Teste bem-sucedido!";
	}
	
	@PostMapping
	public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.senha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
		if(this.usuarioRepository.findByLogin(registerDTO.login()) != null || 
		   this.usuarioRepository.findByEmail(registerDTO.email()) != null) {
			return ResponseEntity.badRequest().body("Login ou e-mail já estão em uso");
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.senha());
		Usuario newUsuario = new Usuario(registerDTO.login(), encryptedPassword, registerDTO.email());
		this.usuarioRepository.save(newUsuario);
		
		return ResponseEntity.ok("Usuário registrado com sucesso!");
	}
}
