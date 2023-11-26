package com.parcialpapeleria.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcialpapeleria.app.entity.Cliente;
import com.parcialpapeleria.app.exception.NotFoundException;
import com.parcialpapeleria.app.repository.ClienteRepository;

@Controller // Asegúrate de agregar la anotación @Controller
@RequestMapping("/clientes")
public class ClienteTemplateController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/")
	public String ClientesList(Model model) {
		model.addAttribute("clientes", clienteRepository.findAll());
		return "administrador/clientes-list";
	}
	
	@GetMapping("/new")
	public String ClientesNew(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "administrador/clientes-form";
	}
	
	@GetMapping("/edit/{id}")
	public String ClienteEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("cliente", clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente no encontrado")));
		return "administrador/clientes-form";
	}
	
	@PostMapping("/save")
	public String ClienteSave(@ModelAttribute("cliente") Cliente cliente) {
		if (cliente.getId().isEmpty()) {
			cliente.setId(null);
		}

		clienteRepository.save(cliente);
		return "redirect:/clientes/";
	}
	
	@GetMapping("/delete/{id}")
	public String ClienteDelete(@PathVariable("id") String id) {
		clienteRepository.deleteById(id);
		return "redirect:/clientes/";
	}
	
	/* EMPLEADO */
	
	@GetMapping("/list/")
	public String ClientesListEmp(Model model) {
		model.addAttribute("clientes", clienteRepository.findAll());
		return "empleado/clientes-list-emp";
	}
	
	@GetMapping("/newemp")
	public String ClientesNewEmp(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "empleado/clientes-form-emp";
	}
	
	@GetMapping("/editemplead/{id}")
	public String ClienteEditEmpl(@PathVariable("id") String id, Model model) {
		model.addAttribute("cliente", clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente no encontrado")));
		return "empleado/clientes-form-emp";
	}
	
	@PostMapping("/saveempl")
	public String ClienteSaveEmpl(@ModelAttribute("cliente") Cliente cliente) {
		if (cliente.getId().isEmpty()) {
			cliente.setId(null);
		}

		clienteRepository.save(cliente);
		return "redirect:/clientes/list/";
	}

}
