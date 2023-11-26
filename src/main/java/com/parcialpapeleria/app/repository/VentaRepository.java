package com.parcialpapeleria.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcialpapeleria.app.entity.Venta;

public interface VentaRepository extends MongoRepository<Venta, String>{

}
