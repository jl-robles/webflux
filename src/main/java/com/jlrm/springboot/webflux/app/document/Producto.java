package com.jlrm.springboot.webflux.app.document;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection="productos")
public class Producto {

	@Id
	private String id;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private Double price;
	
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date createAt;
	
	@Valid
	private Categoria categoria;
	
	private String foto;
	
	public Producto(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public Producto(String name, double price, Categoria categoria) {
		this(name, price);
		this.categoria = categoria;
	}

	public Producto() {
	}
}
