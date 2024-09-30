package com.pecasDelivery.AutoPecas_Delivery_API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pecasDelivery.AutoPecas_Delivery_API.dto.MarcaDTO;
import com.pecasDelivery.AutoPecas_Delivery_API.model.Marca;
import com.pecasDelivery.AutoPecas_Delivery_API.repositories.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;
	
	public Marca createMarca(MarcaDTO dto){
		Marca marca = new Marca();
		marca.setNome(dto.nome());
		marcaRepository.save(marca);
		return marca; //verificar se preciso retornar a marca
	}
	
	public List<Marca> listarMarca() {
		return marcaRepository.findAll();
	}
}
