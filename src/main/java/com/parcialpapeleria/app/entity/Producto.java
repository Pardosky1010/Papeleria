package com.parcialpapeleria.app.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public class Producto {

	@Id
	private String id;
	
	@NotEmpty
	private String referencia;
	
	@NotEmpty
	private String marca;
	
	@NotEmpty
	private String descripcion;
	
	@NotEmpty
	private String categoria;
	
	@NotEmpty
	private String precio;

	@NotEmpty
	private String disponibilidad;
	
	public Producto() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
}
