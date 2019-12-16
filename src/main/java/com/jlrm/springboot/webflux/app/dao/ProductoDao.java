package com.jlrm.springboot.webflux.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.jlrm.springboot.webflux.app.document.Producto;

public interface ProductoDao extends ReactiveMongoRepository<Producto, String>{

}
