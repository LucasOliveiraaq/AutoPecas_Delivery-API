package com.pecasDelivery.AutoPecas_Delivery_API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pecasDelivery.AutoPecas_Delivery_API.dto.CategoriaDTO;
import com.pecasDelivery.AutoPecas_Delivery_API.model.Categoria;
import com.pecasDelivery.AutoPecas_Delivery_API.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria createCategoria(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaDTO.nome());
		categoriaRepository.save(categoria);
		return categoria;
	}
	
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}
}
