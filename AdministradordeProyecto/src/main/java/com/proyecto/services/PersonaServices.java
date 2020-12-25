package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Persona;
import com.proyecto.repository.PersonaRepository;

@Service
public class PersonaServices {
	@Autowired
	private PersonaRepository personaRepository;
	
	public void createPersona(Persona persona) {
		personaRepository.save(persona);
	}
}
