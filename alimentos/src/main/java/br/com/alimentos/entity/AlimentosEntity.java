package br.com.alimentos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;



@Entity
@Table(name = "alimentos")
public class AlimentosEntity extends RepresentationModel<AlimentosEntity>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotBlank(message = "O nome do alimento é obrigatorio!")
	private String name;
	
	//@NotBlank(message = "O preço do alimento é obrigatorio!")
	private Double price;
	
	//@NotBlank(message = "A validade do produto é obrigatoria!")
	private String validity;
	
	//@NotBlank(message = "A quantidade do produto é obrigatoria!")
	private Integer quantity;
	
	public AlimentosEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	

}
