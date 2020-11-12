package com.empleados.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20, nullable = false)
	private String nombre;
	@Column(length = 50, nullable = true)
	private String segundoNombre;
	@Column(length = 20, nullable = false)
	private String apellido;
	@Column(length = 20, nullable = false)
	private String segundoApellido;
	@Column(nullable = false)
	private String pais;
	
	@JoinColumn(name = "identificaion_id")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
	@JsonIgnoreProperties("empleados")
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(unique = true, length = 10)
	private String documento;
	@Email
	@Column(length = 300, unique = true, nullable = false)
	private String correo;
	
	@JoinColumn(name = "area_id")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
	@JsonIgnoreProperties("empleados")
	private Area area;
	private String estado = "activo";
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaRegistro;

	public Empleado(String nombre, String segundoNombre, String apellido, String segundoApellido, String pais,
			String documento, @Email String correo, String estado , Calendar fechaRegistro) {
		super();
		this.nombre = nombre;
		this.segundoNombre = segundoNombre;
		this.apellido = apellido;
		this.segundoApellido = segundoApellido;
		this.pais = pais;
		this.documento = documento;
		this.correo = correo;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
	}
	
	public Empleado() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
