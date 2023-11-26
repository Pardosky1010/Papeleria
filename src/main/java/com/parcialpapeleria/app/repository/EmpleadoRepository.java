package com.parcialpapeleria.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcialpapeleria.app.entity.Empleado;

public interface EmpleadoRepository extends MongoRepository<Empleado, String>{

	Empleado findByUserAndPassword(String user, String password);
}
