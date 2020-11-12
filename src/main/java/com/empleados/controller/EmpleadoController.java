package com.empleados.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empleados.dto.Mensaje;
import com.empleados.entity.Empleado;
import com.empleados.excepciones.ResourceNotFoundException;
import com.empleados.repository.AreaRepository;
import com.empleados.repository.TipoIdRepository;
import com.empleados.service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
@CrossOrigin
public class EmpleadoController {

	Empleado empleado;

	@Autowired
	EmpleadoService empleadoService;
	@Autowired
	AreaRepository areaRepository;
	@Autowired
	TipoIdRepository tipoIdRepository;

	@GetMapping("/listar")
	public ResponseEntity<List<Empleado>> getAllEpleados() {
		List<Empleado> empleados = empleadoService.getEmpleados();
		return new ResponseEntity<List<Empleado>>(empleados, HttpStatus.OK);
	}

	@PostMapping("/guardar/area/{area_id}/tipo/{identificacion_id}")
	public ResponseEntity<Empleado> saveEmpleado(@Valid @RequestBody Empleado empleado, BindingResult bindingResult,
			@PathVariable("area_id") int area_id, @PathVariable("identificacion_id") int identificacion_id) {

		if (bindingResult.hasFieldErrors())// valido los campos
			return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isAllBlank(empleado.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

		if (empleadoService.existePorNombre(empleado.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);

		if (empleadoService.existePorDocumento(empleado.getDocumento()))
			return new ResponseEntity(new Mensaje("El documento ingresado ya existe"), HttpStatus.BAD_REQUEST);
		if (empleadoService.existePorCorreo(empleado.getCorreo()))
			return new ResponseEntity(new Mensaje("El correo ingresado ya existe"), HttpStatus.BAD_REQUEST);

		this.empleado = empleado;
		areaRepository.findById(area_id).map((area) -> {
			this.empleado.setArea(area);
			return this.empleado;
		}).orElseThrow(() -> new ResourceNotFoundException("Area", "id", area_id));
		tipoIdRepository.findById(identificacion_id).map((tipoId) -> {
			this.empleado.setTipoIdentificacion(tipoId);
			return this.empleado;
		}).orElseThrow(() -> new ResourceNotFoundException("TipoIdentificacion", "id", identificacion_id));
		this.empleadoService.guardar(empleado);
		return new ResponseEntity(new Mensaje("Empleado creado"), HttpStatus.OK);
	}

	@PutMapping("/editar/area/{area_id}/tipo/{identificacion_id}/emp/{id}")
	public ResponseEntity<Empleado> editarEmpleado(@Valid @RequestBody Empleado empleado, BindingResult bindingResult,
			@PathVariable("area_id") int area_id, @PathVariable("identificacion_id") int identificacion_id,
			@PathVariable("id") int id) {

		if (!areaRepository.existsById(area_id))
			return new ResponseEntity(new Mensaje("Este area no exite"), HttpStatus.BAD_REQUEST);
		if (!tipoIdRepository.existsById(identificacion_id))
			return new ResponseEntity(new Mensaje("Este tipo de documento no exite"), HttpStatus.BAD_REQUEST);
		if (!empleadoService.existePorId(id))
			return new ResponseEntity(new Mensaje("Este empleado no exite"), HttpStatus.BAD_REQUEST);
		if (bindingResult.hasFieldErrors())// valido los campos
			return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isAllBlank(empleado.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (empleadoService.existePorNombre(empleado.getNombre())
				&& empleadoService.getByNombreEmpleado(empleado.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje("El nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);

		if (empleadoService.existePorDocumento(empleado.getDocumento()))
			return new ResponseEntity(new Mensaje("El documento ingresado ya existe"), HttpStatus.BAD_REQUEST);
		if (empleadoService.existePorCorreo(empleado.getCorreo()))
			return new ResponseEntity(new Mensaje("El correo ingresado ya existe"), HttpStatus.BAD_REQUEST);

		Empleado emp = this.empleadoService.getEmpleadoById(id).get();
		emp.setNombre(empleado.getNombre());
		emp.setSegundoNombre(empleado.getSegundoNombre());
		emp.setApellido(empleado.getApellido());
		emp.setSegundoApellido(empleado.getSegundoApellido());
		emp.setPais(empleado.getPais());
		emp.setDocumento(empleado.getDocumento());
		emp.setCorreo(empleado.getCorreo());
		emp.setFechaRegistro(empleado.getFechaRegistro());
		this.empleadoService.guardar(emp);
		return new ResponseEntity(new Mensaje("Empleado actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") int id) {
		if (!empleadoService.existePorId(id)) {
			return new ResponseEntity(new Mensaje("Este empleado no existe"), HttpStatus.BAD_REQUEST);
		}
		empleadoService.eliminar(id);
		return new ResponseEntity(new Mensaje("Empleado eliminado"), HttpStatus.OK);
	}

}
