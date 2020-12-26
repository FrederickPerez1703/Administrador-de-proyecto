package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Persona;
import com.proyecto.repository.PersonaRepository;
import com.proyecto.utilidades.EncriptarContrasena;

@Service
public class PersonaServices {
	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private EncriptarContrasena encriptarContrasena;
	
	public void createPersona(Persona persona) {
		String newPassword = encriptarContrasena.passwordEncoder()
				.encode(persona.getPassword());
		persona.setPassword(newPassword);
		personaRepository.save(persona);
	}
}
