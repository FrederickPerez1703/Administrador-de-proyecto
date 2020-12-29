package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Persona;
import com.proyecto.excepciones.BadRequestException;
import com.proyecto.repository.PersonaRepository;
import com.proyecto.security.LoginUsuario;

@Service
public class ValidacionService {

	@Autowired
	private PersonaRepository personaRepository;

	public boolean isValidarPersona(Persona persona) {
		if (persona.getNombre().equals(null) || persona.getApellido().equals(null) || persona.getEmail().equals(null)
				|| persona.getUsuario().equals(null) || persona.getPassword().equals(null)) {
			return false;
		} else if (persona.getNombre().isBlank() || persona.getApellido().isBlank() || persona.getEmail().isBlank()
				|| persona.getUsuario().isBlank() || persona.getPassword().isBlank()) {
			throw new BadRequestException("No se permite valores vacios");
		} else if (ExiteEmail(persona.getEmail()) != null) {
			throw new BadRequestException("Este E-mail Existe");
		} else if (ExiteUsuario(persona.getUsuario()) != null) {
			throw new BadRequestException("Este Usuario Existe");
		} else if (!isValidrLongitudCampos(persona)) {
			throw new BadRequestException("Haz sobre pasado el limite de este campo");
		} else {
			return true;
		}
	}

	public boolean isValidarLoginUsuario(LoginUsuario loginUsuario) {
		if (loginUsuario.getPassword().equals(null) || loginUsuario.getPassword().isBlank()
				|| loginUsuario.getUsuario().equals(null) || loginUsuario.getUsuario().isBlank()) {
			throw new BadRequestException("Algo a salio mal el USUARIO O PASSWORD es incorrecto");
		}
		return true;
	}

	private Persona ExiteEmail(String email) {
		return personaRepository.findByEmail(email);
	}

	private Persona ExiteUsuario(String usuario) {
		return personaRepository.findByUsuario(usuario);
	}

	private boolean isValidrLongitudCampos(Persona persona) {
		if (persona.getNombre().length() > 50 || persona.getApellido().length() > 50
				|| persona.getEmail().length() > 100
				|| (persona.getUsuario().length() > 50 || persona.getPassword().length() > 100)) {
			return false;
		}
		return true;
	}
}
