package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.entidad.Proyecto;
import com.proyecto.services.ProyectoServices;
import com.proyecto.services.ValidacionService;

@RestController
@RequestMapping("/Proyecto")
public class RestAdministradorProyecto {

	private ProyectoServices proyectoServices;
	private ValidacionService validacionService;

	@Autowired
	public RestAdministradorProyecto(ProyectoServices proyectoServices, ValidacionService validacionService) {
		this.proyectoServices = proyectoServices;
		this.validacionService = validacionService;
	}

	@GetMapping("/Home")
	@ResponseBody
	public String home() {
		return "Welcome";
	}

	@GetMapping("/Lista/{nombreProyecto}")
	public ResponseEntity<?> proyecto(@PathVariable String nombreProyecto) {
		if (validacionService.isValidarVariableUrl(nombreProyecto)) {
			Proyecto dataProyecto = proyectoServices.proyecto(nombreProyecto);
			return ResponseEntity.ok(dataProyecto);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verifica nuevamente el nombre del Proyecto");
	}

	@PostMapping("/CrearProyecto")
	public ResponseEntity<?> crearProyecto(@RequestBody Proyecto proyecto) {
		if (validacionService.isValidarProyecto(proyecto)) {
			proyectoServices.crearProyecto(proyecto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Creado Con exito");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Error");
	}
}
