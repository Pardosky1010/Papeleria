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
import com.parcialpapeleria.app.entity.Venta;
import com.parcialpapeleria.app.exception.NotFoundException;
import com.parcialpapeleria.app.repository.VentaRepository;

@RestController
@RequestMapping(value = "/api/ventas")
public class VentaController {

	@Autowired
    private VentaRepository ventaRepository;
	
	@GetMapping("/")
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable String id) {
        return ventaRepository.findById(id).orElseThrow(() -> new NotFoundException("Venta no encontrada"));
    }

    @PostMapping("/")
    public Venta saveVenta(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Venta venta = mapper.convertValue(body, Venta.class);
        return ventaRepository.save(venta);
    }

    @PutMapping("/{id}")
    public Venta updateVenta(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Venta venta = mapper.convertValue(body, Venta.class);
        venta.setId(id);
        return ventaRepository.save(venta);
    }

    @DeleteMapping("/{id}")
    public Venta deleteVenta(@PathVariable String id) {
    	Venta venta = ventaRepository.findById(id).orElseThrow(() -> new NotFoundException("Venta no encontrada"));
    	ventaRepository.deleteById(id);
        return venta;
    }
}
