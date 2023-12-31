package com.parcialpapeleria.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.parcialpapeleria.app.entity.Producto;
import com.parcialpapeleria.app.entity.Venta;
import com.parcialpapeleria.app.repository.ProductoRepository;
import com.parcialpapeleria.app.repository.VentaRepository;
import com.parcialpapeleria.app.utilities.ListarVentasPdf;

import jakarta.servlet.http.HttpSession;

import com.parcialpapeleria.app.exception.NotFoundException;

@Controller // Asegúrate de agregar la anotación @Controller
@RequestMapping("/ventas")
public class VentaTemplateController {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ListarVentasPdf listadoVentasPDF;
	
	@GetMapping("/")
	public String Catalogo(Model model) {
		model.addAttribute("ventas", ventaRepository.findAll());
		return "cliente/compras-list";
	}
	
	@GetMapping("/new")
	public String ventaNewTemplate(Model model, Model modeloproducto) {
		
		List<Producto> productos = productoRepository.findAll();
		modeloproducto.addAttribute("productos", productos);
		model.addAttribute("venta", new Venta());
		return "cliente/compras-form";
	}
	
	@PostMapping("/save")
	public String VentaSave(@ModelAttribute("venta") Venta venta) {
		if (venta.getId().isEmpty()) {
			venta.setId(null);
		}

		ventaRepository.save(venta);
		return "redirect:/ventas/";
	}
	
	@GetMapping("/delete/{id}")
	public String VentaDelete(@PathVariable("id") String id) {
		ventaRepository.deleteById(id);
		return "redirect:/ventas/";
	}
	
	@GetMapping("/edit/{id}")
	public String ventaEditTemplate(@PathVariable("id") String id, Model model, Model modeloproducto) {
		model.addAttribute("ventas",ventaRepository.findById(id).orElseThrow(() -> new NotFoundException("Venta no encontrada")));
		List<Producto> producto = productoRepository.findAll();

		model.addAttribute("productos", producto);
		return "cliente/compras-form";
	}
	
	// Metodo para generar Pdf
		@GetMapping("/pdf")
		public ModelAndView generarPdf(Model model) {

			List<Venta> listadoVentas = ventaRepository.findAll();
			
			// Crea el modelo con los datos que deseas pasar a la vista PDF
			Map<String, Object> model1 = new HashMap<>();
			model1.put("ventas", listadoVentas);

			return new ModelAndView(listadoVentasPDF, model1);
		}
}
