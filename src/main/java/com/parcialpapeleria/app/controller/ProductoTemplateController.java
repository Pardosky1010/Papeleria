package com.parcialpapeleria.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcialpapeleria.app.entity.Producto;
import com.parcialpapeleria.app.exception.NotFoundException;
import com.parcialpapeleria.app.repository.ProductoRepository;

@Controller // Asegúrate de agregar la anotación @Controller
@RequestMapping("/productos")
public class ProductoTemplateController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping("/new")
	public String productoNew(Model model) {
		model.addAttribute("producto", new Producto());
		return "administrador/productos-form";
	}
	
	@GetMapping("/edit/{id}")
	public String ProducEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("producto", productoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Producto no encontrado")));
		return "administrador/productos-form";
	}
	
	@PostMapping("/save")
	public String ProductSave(@ModelAttribute("producto") Producto producto) {
		if (producto.getId().isEmpty()) {
			producto.setId(null);
		}

		if (producto.getDisponibilidad().isEmpty()) {
			producto.setDisponibilidad("Disponible");
		}

		productoRepository.save(producto);
		return "redirect:/productos/list/";
	}
	
	
	@GetMapping("/delete/{id}")
	public String ProductDelete(@PathVariable("id") String id) {
		productoRepository.deleteById(id);
		return "redirect:/productos/list/";
	}
	
	/* METODO HOME */
	@GetMapping("/")
	public String Catalogo(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "catalogo";
	}
	
	@GetMapping("/list/")
	public String ProductosList(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "administrador/productos-list";
	}
	
	@GetMapping("/puntos/")
	public String Puntos(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "puntosatencion";
	}
	
	
	/* METODO ADMINISTRADOR */
	@GetMapping("/catadmin/")
	public String CatalogoAdmin(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "catalogo/catalogo-admin";
	}
	
	@GetMapping("/puntosad/")
	public String PuntosAdmin(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "puntos/puntosatencion-admin";
	}
	
	
	/* METODO EMPLEADO */
	@GetMapping("/catemple/")
	public String CatalogoEmple(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "catalogo/catalogo-empleado";
	}
	
	@GetMapping("/puntosemp/")
	public String PuntosEmp(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "puntos/puntosatencion-empleado";
	}
	
	@GetMapping("/listp/")
	public String ProductosListEmp(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "empleado/productos-list-emp";
	}
	
	@GetMapping("/newempl")
	public String productoNewEmp(Model model) {
		model.addAttribute("producto", new Producto());
		return "empleado/productos-form-emp";
	}
	
	@GetMapping("/editemp/{id}")
	public String ProducEditEmp(@PathVariable("id") String id, Model model) {
		model.addAttribute("producto", productoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Producto no encontrado")));
		return "empleado/productos-form-emp";
	}
	
	@PostMapping("/saveemp")
	public String ProductSaveEmp(@ModelAttribute("producto") Producto producto) {
		if (producto.getId().isEmpty()) {
			producto.setId(null);
		}

		if (producto.getDisponibilidad().isEmpty()) {
			producto.setDisponibilidad("Disponible");
		}

		productoRepository.save(producto);
		return "redirect:/productos/listp/";
	}
	
	
	/* METODO CLIENTE */
	@GetMapping("/catclient/")
	public String CatalogoCliente(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "catalogo/catalogo-cliente";
	}
	
	@GetMapping("/puntoscli/")
	public String PuntosCli(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "puntos/puntosatencion-cliente";
	}
}
