package com.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.empleados.entity.TipoIdentificacion;
import com.empleados.service.TipoIdService;

@RestController
@RequestMapping("/tipo")
@CrossOrigin
public class TipoIdController {

	@Autowired
	TipoIdService tipoIdService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<TipoIdentificacion>>listadoTipos(){
		List<TipoIdentificacion> tipos = this.tipoIdService.getAllTipos();
		return new ResponseEntity<List<TipoIdentificacion>>(tipos,HttpStatus.OK);
	}
}
