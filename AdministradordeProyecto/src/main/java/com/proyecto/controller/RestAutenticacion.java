package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.entidad.Persona;
import com.proyecto.excepciones.BadRequestException;
import com.proyecto.security.LoginUsuario;
import com.proyecto.security.UsuarioPrincipal;
import com.proyecto.services.PersonaServices;
import com.proyecto.services.UsuarioServices;
import com.proyecto.services.ValidacionService;

@RestController
@RequestMapping("/Autenticacion")
public class RestAutenticacion {

	@Autowired
	private ValidacionService validacionService;
	@Autowired(required = true)
	private PersonaServices personaServices;
	@Autowired(required = true)
	private UsuarioServices usuarioServices;
	@Autowired(required = true)
	private AuthenticationManager authenticationManager;
	private UsuarioPrincipal usuarioPrincipal;

	@PostMapping("/Registro")
	public ResponseEntity<?> createPersona(@RequestBody Persona persona) {
		if (validacionService.isValidarPersona(persona)) {
			personaServices.createPersona(persona);
			usuarioServices.createUsuario(persona);
			return ResponseEntity.status(HttpStatus.CREATED).body("Registrado Con Exito");
		}
		throw new BadRequestException("No se permite valores nulos");
	}

	@PostMapping("/Login")
	public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario) {
		if (validacionService.isValidarLoginUsuario(loginUsuario)) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUsuario.getUsuario(), loginUsuario.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
			usuarioServices.usuariologin(usuarioPrincipal.getUsername());
			return ResponseEntity.status(HttpStatus.OK).body("Bienvenido ".concat(usuarioPrincipal.getUsername()));
		}
		throw new BadRequestException("No se permite valores nulos(LOL)");
	}
}
