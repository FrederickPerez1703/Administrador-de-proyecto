package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Persona;
import com.proyecto.entidad.Usuario;
import com.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository usuarioRepository;
	private Usuario usuario;

	public void createUsuario(Persona persona) {
		Usuario usuario = new Usuario();
		usuario.setUsuario(persona.getUsuario());
		usuario.setPassword(persona.getPassword());
		usuario.setPersona(persona);
		usuario.setRol("USER");
		usuarioRepository.save(usuario);
	}

	public void usuariologin(String usuario) {
		this.usuario = usuarioRepository.findByUsuario(usuario);
	}

	public Usuario getUsuUsuario() {
		return this.usuario;
	}
}
