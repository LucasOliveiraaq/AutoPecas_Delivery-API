package com.pecasDelivery.AutoPecas_Delivery_API.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UsuarioController {

	@GetMapping("/test")
	public String test() {
		return "Teste bem-sucedido!";
	}
}
