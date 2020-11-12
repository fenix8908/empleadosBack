package com.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empleados.entity.Area;
import com.empleados.service.AreaService;

@RestController
@RequestMapping("/area")
@CrossOrigin
public class AreaController {
	
	@Autowired
	AreaService areaService;

	@GetMapping("/lista")
	public ResponseEntity<List<Area>>listadoAreas(){
		List<Area> areas = this.areaService.getAllAreas();
		return new ResponseEntity<List<Area>>(areas,HttpStatus.OK);
	}
}
