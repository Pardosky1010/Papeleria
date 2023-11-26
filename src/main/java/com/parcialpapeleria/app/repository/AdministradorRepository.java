package com.parcialpapeleria.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcialpapeleria.app.entity.Administrador;

public interface AdministradorRepository extends MongoRepository<Administrador, String>{

	Administrador findByUserAndPassword(String user, String password);
	
}
