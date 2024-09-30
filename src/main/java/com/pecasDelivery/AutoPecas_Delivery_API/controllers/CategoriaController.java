package com.pecasDelivery.AutoPecas_Delivery_API.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pecasDelivery.AutoPecas_Delivery_API.dto.CategoriaDTO;
import com.pecasDelivery.AutoPecas_Delivery_API.model.Categoria;
import com.pecasDelivery.AutoPecas_Delivery_API.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("produto/categoria")
@RequiredArgsConstructor
public class CategoriaController {

	@Autowired 
	private CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
		try {
			return ResponseEntity.ok(categoriaService.createCategoria(categoriaDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/list")
	public List<Categoria> listCategorias() {
		return categoriaService.listarCategorias();
	}
}
