package com.pecasDelivery.AutoPecas_Delivery_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pecasDelivery.AutoPecas_Delivery_API.dto.MarcaDTO;
import com.pecasDelivery.AutoPecas_Delivery_API.service.MarcaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("produto/marca")
@RequiredArgsConstructor
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	
	@PostMapping
	public ResponseEntity createMarca(@RequestBody MarcaDTO dto) {
		try {
			return ResponseEntity.ok(marcaService.createMarca(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
