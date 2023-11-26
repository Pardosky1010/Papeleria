package com.parcialpapeleria.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcialpapeleria.app.repository.AdministradorRepository;
import com.parcialpapeleria.app.exception.NotFoundException;
import com.parcialpapeleria.app.entity.Administrador;

@Controller
@RequestMapping("/administradores")
public class AdministradorTemplateController {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	@GetMapping("/")
	public String AdministradoresList(Model model) {
		model.addAttribute("administradores", administradorRepository.findAll());
		return "administrador/administradores-list";
	}
	
	@GetMapping("/new")
	public String AdministradorNew(Model model) {
		model.addAttribute("administrador", new Administrador());
		return "administrador/administradores-form";
	}
	
	@GetMapping("/edit/{id}")
	public String AdministradorEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("administrador", administradorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Administrador no encontrado")));
		return "administrador/administradores-form";
	}
	
	@PostMapping("/save")
	public String AdministradorSave(@ModelAttribute("administrador") Administrador administrador) {
		if (administrador.getId().isEmpty()) {
			administrador.setId(null);
		}

		administradorRepository.save(administrador);
		return "redirect:/administradores/";
	}
	
	@GetMapping("/delete/{id}")
	public String AdministradorDelete(@PathVariable("id") String id) {
		administradorRepository.deleteById(id);
		return "redirect:/administradores/";
	}
}
