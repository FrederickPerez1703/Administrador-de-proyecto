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
import com.proyecto.excepciones.BadRequestException;
import com.proyecto.services.ProyectoServices;
import com.proyecto.services.ValidacionService;

@RestController
@RequestMapping("/Proyecto")
public class RestAdministradorProyecto {
	@Autowired
	private ProyectoServices proyectoServices;
	@Autowired
	private ValidacionService validacionService;
	
	@GetMapping("/Home")
	@ResponseBody
	public String home() {
		return "Welcome";
	}
	
	@GetMapping("/Lista/{nombreProyecto}")
	public ResponseEntity<?> proyecto(@PathVariable String nombreProyecto) {
		if (validacionService.isValidarVariableUrl(nombreProyecto)) {
			Proyecto dataProyecto = proyectoServices.proyecto(nombreProyecto);
			if (dataProyecto == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este Proyecto le pertenece a otra persona.");
			}
			return ResponseEntity.ok(dataProyecto);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se permite valores null o Vacios");
	}

	@PostMapping("/CrearProyecto")
	public ResponseEntity<?> crearProyecto(@RequestBody Proyecto proyecto) {
		try {
			proyectoServices.crearProyecto(proyecto);
		} catch (Exception e) {
			throw new BadRequestException("Este Usuario ya tiene un proyecto en proceso");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Creado Con exito");
	}

}
