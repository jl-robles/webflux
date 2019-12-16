package com.jlrm.springboot.webflux.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.jlrm.springboot.webflux.app.document.Categoria;

public interface CategoriaDao extends ReactiveMongoRepository<Categoria, String	>{

}
