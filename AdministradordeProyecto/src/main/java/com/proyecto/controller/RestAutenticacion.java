package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.entidad.Persona;
import com.proyecto.excepciones.BadRequestException;
import com.proyecto.services.PersonaServices;
import com.proyecto.services.ValidacionService;

@RestController
@RequestMapping("/Autenticacion")
public class RestAutenticacion {
	 
	@Autowired
	private ValidacionService validacionService;
	@Autowired(required = true)
	private PersonaServices personaServices; 
	
	@PostMapping("/Registro")
	public ResponseEntity<?> crearPersona(@RequestBody Persona persona){
		if(validacionService.isValidarPersona(persona)) {
			personaServices.createPersona(persona);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Registrado Con Exito");
		}
		throw new BadRequestException("No se permite valores nulos");
	}
}
