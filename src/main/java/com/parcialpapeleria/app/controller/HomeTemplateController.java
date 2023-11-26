package com.parcialpapeleria.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeTemplateController {

	@GetMapping("/")
	public String homeTemplate(Model model, HttpSession session) {
		return "home";
	}

	@GetMapping("/cliente")
	public String homeClienteTemplate(Model model, HttpSession session) {
		return "home/home-cliente";
	}

	@GetMapping("/empleado")
	public String homeEmpleadoTemplate(Model model, HttpSession session) {
		return "home/home-empleado";
	}

	@GetMapping("/administrador")
	public String homeAdministradorTemplate(Model model, HttpSession session) {
		return "home/home-administrador";
	}
}
