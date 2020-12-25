package com.proyecto.services;

import org.springframework.stereotype.Service;
import com.proyecto.entidad.Persona;

@Service
public class ValidacionService {
	
	public boolean isValidarPersona(Persona persona) {
		if(persona.equals(null) || persona.getNombre().equals(null) || persona.getApellido().equals(null) || persona.getEmail().equals(null)
				|| persona.getUsuario().equals(null) || persona.getPassword().equals(null)) {
			return false;
		}else if(persona.getNombre().isBlank()|| persona.getApellido().isBlank() || persona.getEmail().isBlank()
				|| persona.getUsuario().isBlank() || persona.getPassword().isBlank()) {
			return false;
		}
		return true;
	}
}
