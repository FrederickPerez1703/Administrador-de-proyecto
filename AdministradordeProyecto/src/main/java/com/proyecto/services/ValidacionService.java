package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.entidad.Persona;
import com.proyecto.excepciones.BadRequestException;
import com.proyecto.repository.PersonaRepository;

@Service
public class ValidacionService {

	@Autowired
	private PersonaRepository personaRepository;

	public boolean isValidarPersona(@RequestBody Persona persona) {
		if (persona.getNombre().equals(null) || persona.getApellido().equals(null)
				|| persona.getEmail().equals(null) || persona.getUsuario().equals(null)
				|| persona.getPassword().equals(null)) {
			return false;
		} else if (persona.getNombre().isBlank() || persona.getApellido().isBlank() || persona.getEmail().isBlank()
				|| persona.getUsuario().isBlank() || persona.getPassword().isBlank()) {
			return false;
		} else if (isExiteEmail(persona.getEmail()) != null) {
			throw new BadRequestException("Este E-mail Existe");
		} else if (isExiteUsuario(persona.getUsuario()) != null) {
			throw new BadRequestException("Este Usuario Existe");
		} else {
			return true;
		}
	}

	private Persona isExiteEmail(String email) {
		return personaRepository.findByEmail(email);
	}

	private Persona isExiteUsuario(String usuario) {
		return personaRepository.findByUsuario(usuario);
	}
}
