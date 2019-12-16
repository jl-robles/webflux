package com.jlrm.springboot.webflux.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.jlrm.springboot.webflux.app.document.Categoria;
import com.jlrm.springboot.webflux.app.document.Producto;
import com.jlrm.springboot.webflux.app.service.IProductoService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

	@Autowired
	private IProductoService service;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mongoTemplate.dropCollection("productos").subscribe();
		mongoTemplate.dropCollection("categorias").subscribe();

		Categoria electronica = new Categoria("Electrónica");
		Categoria deporte = new Categoria("Deporte");
		Categoria compu = new Categoria("Computación");
		Categoria muebles = new Categoria("Muebles");

		Flux.just(electronica, deporte, compu, muebles)
		.flatMap(service::saveCategoria)
		.doOnNext(c -> {
			log.info("Categoria creada: " + c.getName() + " Id: " + c.getId());
		}).thenMany(Flux.just(new Producto("TV Panasonic LED 55", 7500, electronica),
				new Producto("Sony Camara Digital", 2500, electronica),
				new Producto("Samsung Galaxy Note 5", 22500, electronica), new Producto("Mouse Logitech", 750, compu),
				new Producto("Asus Core i7", 19000, compu), new Producto("Monitor Samsung 27", 4000, compu),
				new Producto("Huawei NP30", 6500, electronica), new Producto("Ipad 5", 9500, electronica),
				new Producto("Bicicleta Benotto R26", 4500, deporte), new Producto("Sillon individual", 3500, muebles),
				new Producto("LG Monitor 26", 4500, electronica)).flatMap(producto -> {
					producto.setCreateAt(new Date());
					return service.save(producto);
				})).subscribe(producto -> log.info("insert " + producto.getId() + " " + producto.getName()));

	}

}
