package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Persona;
import com.proyecto.entidad.Proyecto;
import com.proyecto.excepciones.BadRequestException;
import com.proyecto.excepciones.NotFoundExcepcion;
import com.proyecto.repository.PersonaRepository;
import com.proyecto.repository.ProyectoRepository;
import com.proyecto.security.LoginUsuario;

@Service
public class ValidacionService {

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private ProyectoRepository proyectoRepository;
	@Autowired
	private UsuarioServices usuarioServices;

	public boolean isValidarPersona(Persona persona) {
		if (persona.getNombre() == null || persona.getApellido() == null || persona.getEmail() == null
				|| persona.getUsuario() == null || persona.getPassword() == null) {
			return false;
		} else if (persona.getNombre().isBlank() || persona.getApellido().isBlank() || persona.getEmail().isBlank()
				|| persona.getUsuario().isBlank() || persona.getPassword().isBlank()) {
			throw new BadRequestException("No se permite valores vacios");
		} else if (exiteEmail(persona.getEmail()) != null) {
			throw new BadRequestException("Este E-mail Existe");
		} else if (exiteUsuario(persona.getUsuario()) != null) {
			throw new BadRequestException("Este Usuario Existe");
		} else if (!isValidrLongitudCampos(persona)) {
			throw new BadRequestException("Haz sobre pasado el limite de este campo");
		} else {
			return true;
		}
	}

	public boolean isValidarLoginUsuario(LoginUsuario loginUsuario) {

		if (loginUsuario.getPassword() == null || loginUsuario.getPassword().isBlank()
				|| loginUsuario.getUsuario() == null || loginUsuario.getUsuario().isBlank()) {
			throw new BadRequestException("Algo a salio mal el USUARIO O PASSWORD es incorrecto");
		}
		return true;
	}

	private Persona exiteEmail(String email) {
		return personaRepository.findByEmail(email);
	}

	private Object exiteUsuario(String usuario) {
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

	public boolean isValidarVariableUrl(String nombreProyecto) {
		if (nombreProyecto == null || nombreProyecto.isBlank()) {
			return false;
		}
		return true;
	}

	public boolean isValidarProyecto(Proyecto proyecto) {
		try {
			Proyecto proyect = proyectoRepository.findByNombreProyecto(proyecto.getNombreProyecto());
			if (proyecto.getComentario().isBlank() || proyecto.getNombreProyecto().isBlank()
			/* || proyecto.getFechaFinalProyecto() == null */) {
				throw new BadRequestException("No se permite valores vacios");
			} else if (proyecto.getParticipante() < 1) {
				throw new BadRequestException("El numero minimo de participante es 1");
			} else if (proyectoRepository.existsByUsuario(usuarioServices.getUsuario())) {
				throw new NotFoundExcepcion("Este usuario tiene un proyecto en proceso..");
			}else if (proyect != null){
				throw new NotFoundExcepcion("Este Nombre de Proyecto ya Existe");
			}else {
				return true;
			}
		}catch(NullPointerException e) {
			return true;
		}
	}
}
