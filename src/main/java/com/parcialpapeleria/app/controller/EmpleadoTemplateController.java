package com.parcialpapeleria.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcialpapeleria.app.entity.Empleado;
import com.parcialpapeleria.app.exception.NotFoundException;
import com.parcialpapeleria.app.repository.EmpleadoRepository;

@Controller // Asegúrate de agregar la anotación @Controller
@RequestMapping("/empleados")
public class EmpleadoTemplateController {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@GetMapping("/")
	public String ClientesList(Model model) {
		model.addAttribute("empleados", empleadoRepository.findAll());
		return "administrador/empleados-list";
	}
	
	@GetMapping("/new")
	public String ClienteNew(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "administrador/empleados-form";
	}
	
	@GetMapping("/edit/{id}")
	public String ClienteEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("empleado", empleadoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Empleado no encontrado")));
		return "administrador/empleados-form";
	}
	
	@PostMapping("/save")
	public String ClienteSave(@ModelAttribute("empleado") Empleado empleado) {
		if (empleado.getId().isEmpty()) {
			empleado.setId(null);
		}

		empleadoRepository.save(empleado);
		return "redirect:/empleados/";
	}
	
	@GetMapping("/delete/{id}")
	public String ClienteDelete(@PathVariable("id") String id) {
		empleadoRepository.deleteById(id);
		return "redirect:/empleados/";
	}
}
