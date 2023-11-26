package com.parcialpapeleria.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcialpapeleria.app.entity.Producto;
import com.parcialpapeleria.app.exception.NotFoundException;
import com.parcialpapeleria.app.repository.ProductoRepository;

@RestController
@RequestMapping(value = "/api/productos")
public class ProductoController {

	@Autowired
    private ProductoRepository productoRepository;
	
	@GetMapping("/")
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable String id) {
        return productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
    }

    @PostMapping("/")
    public Producto saveProducto(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.convertValue(body, Producto.class);
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.convertValue(body, Producto.class);
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public Producto deleteProducto(@PathVariable String id) {
    	Producto producto = productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
    	productoRepository.deleteById(id);
        return producto;
    }
}
