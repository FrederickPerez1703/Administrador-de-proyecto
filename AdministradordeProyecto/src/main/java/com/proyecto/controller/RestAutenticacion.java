package com.proyecto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.entidad.Persona;

@RestController
@RequestMapping("/Autenticacion")
public class RestAutenticacion {
	
	@PostMapping("/Registro")
	public void crearPersona(@RequestBody Persona persona){
		
	}
}
