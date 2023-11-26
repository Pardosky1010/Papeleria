package com.parcialpapeleria.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public class Venta {

	@Id
	private String id;
	
	@NotEmpty
	private String numeroref;
	
	@NotEmpty
	private String fecha;
	
	@NotEmpty
	private List<Producto> producto;
	
	public Venta() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroref() {
		return numeroref;
	}

	public void setNumeroref(String numeroref) {
		this.numeroref = numeroref;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}
	
}
