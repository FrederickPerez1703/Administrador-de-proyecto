package com.proyecto.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Proyecto;
import com.proyecto.repository.ProyectoRepository;

@Service
public class ProyectoServices {

	@Autowired
	private ProyectoRepository proyectoRepository;
	@Autowired
	private UsuarioServices usuarioServices;

	public void crearProyecto(Proyecto proyecto) throws Exception {
		proyecto.setFechaInicioProyecto(new Date());
		proyecto.setFechaFinalProyecto(new Date());
		proyecto.setUsuario(usuarioServices.getUsuUsuario());
		proyectoRepository.save(proyecto);
	}

	public Proyecto proyecto(String nombreProyecto) {
		Proyecto proyecto = proyectoRepository.findByNombreProyecto(nombreProyecto);
		if (usuarioServices.getUsuUsuario().equals(proyecto.getUsuario())) {
			return proyecto;
		}
		return null;
	}
}
