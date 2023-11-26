package com.parcialpapeleria.app.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parcialpapeleria.app.entity.Administrador;
import com.parcialpapeleria.app.entity.Empleado;
import com.parcialpapeleria.app.entity.Cliente;
import com.parcialpapeleria.app.repository.AdministradorRepository;
import com.parcialpapeleria.app.repository.ClienteRepository;
import com.parcialpapeleria.app.repository.EmpleadoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginTemplateController {


	@GetMapping("/")
	public String LoginTemplate(Model model) {
		return "login";
	}
	
	@GetMapping("/registro")
	public String RegistroTemplate(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "registro";

	}
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private AdministradorRepository administradorRepository;
	
	
	@PostMapping("/ingresar")
	public String login(@RequestParam("user") String user, @RequestParam("password") String password,
			Model model, HttpSession session) {
		
		List<Administrador> administradorList = administradorRepository.findAll();
		System.out.println(administradorList.get(0).getUser());
		Administrador administrador = administradorRepository.findByUserAndPassword(user, password);
		if (administrador != null) {
			// Inicio de sesión exitoso, redirigir a la página de inicio
			System.out.println("Correo: " + administrador.getUser() + " Password:" + administrador.getPassword());
			Administrador admin = (Administrador) session.getAttribute("administrador");
			session.setAttribute("administrador", admin);
			model.addAttribute("administrador", administrador);
			return "home/home-administrador"; // Nombre de la página de inicio (por ejemplo, "inicio.html")

		}

		List<Cliente> clienteList = clienteRepository.findAll();
		System.out.println(clienteList.get(0).getUser());
		Cliente cliente = clienteRepository.findByUserAndPassword(user, password);
		if (cliente != null) {
			// Inicio de sesión exitoso, redirigir a la página de inicio
			System.out.println("Correo: " + cliente.getUser() + " Password:" + cliente.getPassword());
			Cliente client = (Cliente) session.getAttribute("cliente");
			session.setAttribute("cliente", client);
			model.addAttribute("cliente", cliente);
			return "home/home-cliente"; // Nombre de la página de inicio (por ejemplo, "inicio.html")
		}
		
		List<Empleado> empleadoList = empleadoRepository.findAll();
		System.out.println(empleadoList.get(0).getUser());
		Empleado empleado = empleadoRepository.findByUserAndPassword(user, password);
		if (empleado != null) {
			// Inicio de sesión exitoso, redirigir a la página de inicio
			System.out.println("Correo: " + empleado.getUser() + " Password:" + empleado.getPassword());
			Empleado emplea = (Empleado) session.getAttribute("empleado");
			session.setAttribute("empleado", emplea);
			model.addAttribute("empleado", empleado);
			return "home/home-empleado"; // Nombre de la página de inicio (por ejemplo, "inicio.html")
		}

		else if (cliente == null || empleado == null || administrador == null) {
			String errorMessage = "Usuario o contraseña incorrectos";
			return "redirect:/login/?error=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
		}
		
		return "/login";
	}
}
