package com.parcialpapeleria.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcialpapeleria.app.entity.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
	
	Cliente findByUserAndPassword(String user, String password);

}
