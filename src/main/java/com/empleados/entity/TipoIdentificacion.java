package com.empleados.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "tipo_identificacion")
public class TipoIdentificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nombreTipo;
	@OneToMany(mappedBy = "tipoIdentificacion")
	private List<Empleado>empleados;
	
	public TipoIdentificacion(String nombreTipo, List<Empleado> empleados) {
		super();
		this.nombreTipo = nombreTipo;
		this.empleados = empleados;
	}
	public TipoIdentificacion() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	
	
}
