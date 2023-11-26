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
import com.parcialpapeleria.app.entity.Empleado;
import com.parcialpapeleria.app.exception.NotFoundException;
import com.parcialpapeleria.app.repository.EmpleadoRepository;


@RestController
@RequestMapping(value = "/api/empleados")
public class EmpleadoController {

	@Autowired
    private EmpleadoRepository empleadoRepository;
	
	@GetMapping("/")
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Empleado getEmpleadoById(@PathVariable String id) {
        return empleadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
    }

    @PostMapping("/")
    public Empleado saveEmpleado(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Empleado empleado = mapper.convertValue(body, Empleado.class);
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public Empleado updateEmpleado(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Empleado empleado = mapper.convertValue(body, Empleado.class);
        empleado.setId(id);
        return empleadoRepository.save(empleado);
    }

    @DeleteMapping("/{id}")
    public Empleado deleteEmpleado(@PathVariable String id) {
    	Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
    	empleadoRepository.deleteById(id);
        return empleado;
    }
}
