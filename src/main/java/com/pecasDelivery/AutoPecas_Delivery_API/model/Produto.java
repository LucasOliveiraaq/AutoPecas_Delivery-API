package com.pecasDelivery.AutoPecas_Delivery_API.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false)
	private BigDecimal preco;
	
	@Column(nullable = false)
	private Integer quantidadeEstoque;
	
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false) 
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "marca_id", nullable = false)
	private Marca marca;
}
