package com.jlrm.springboot.webflux.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlrm.springboot.webflux.app.dao.CategoriaDao;
import com.jlrm.springboot.webflux.app.dao.ProductoDao;
import com.jlrm.springboot.webflux.app.document.Categoria;
import com.jlrm.springboot.webflux.app.document.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService implements IProductoService {
	
	@Autowired
	private ProductoDao dao;
	
	@Autowired
	private CategoriaDao categoriaDao;

	@Override
	public Flux<Producto> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Producto> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Producto> save(Producto producto) {
		return dao.save(producto);
	}

	@Override
	public Mono<Void> delete(Producto producto) {
		return dao.delete(producto);
	}

	@Override
	public Flux<Producto> findAllUppercase() {
		return dao.findAll().map(producto -> {
			producto.setName(producto.getName().toUpperCase());
			return producto;
		});
	}

	@Override
	public Flux<Producto> findAllRepeat() {
		return findAllUppercase().repeat(5000);
	}

	@Override
	public Flux<Categoria> findAllCategoria() {
		return categoriaDao.findAll();
	}

	@Override
	public Mono<Categoria> findCategoriaById(String id) {
		return categoriaDao.findById(id);
	}

	@Override
	public Mono<Categoria> saveCategoria(Categoria categoria) {
		return categoriaDao.save(categoria);
	}

}
