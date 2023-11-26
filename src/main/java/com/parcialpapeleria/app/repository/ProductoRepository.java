package com.parcialpapeleria.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcialpapeleria.app.entity.Producto;


public interface ProductoRepository extends MongoRepository<Producto, String>{
	
}
