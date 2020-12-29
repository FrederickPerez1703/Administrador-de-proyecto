package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Persona;
import com.proyecto.repository.PersonaRepository;

@Service
public class PersonaServices {
	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void createPersona(Persona persona) {
		persona.setPassword(passwordEncoder.encode(persona.getPassword()));
		personaRepository.save(persona);
	}
}
