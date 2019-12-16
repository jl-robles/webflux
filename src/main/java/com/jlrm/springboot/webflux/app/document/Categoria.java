package com.jlrm.springboot.webflux.app.document;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "categorias")
public class Categoria {

	@NotEmpty
	@Id
	private String id;
	private String name;

	public Categoria(String name) {
		this.name = name;
	}

	public Categoria() {
	}

}
