package com.pecasDelivery.AutoPecas_Delivery_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pecasDelivery.AutoPecas_Delivery_API.dto.AuthenticationDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/test")
	public String test() {
		return "Teste bem-sucedido!";
	}
	
	public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.senha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		return ResponseEntity.ok().build();
	}
}
