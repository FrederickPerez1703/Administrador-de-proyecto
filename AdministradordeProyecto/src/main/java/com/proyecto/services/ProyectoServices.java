package com.proyecto.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Proyecto;
import com.proyecto.excepciones.BadRequestException;
import com.proyecto.excepciones.NotFoundExcepcion;
import com.proyecto.repository.ProyectoRepository;

@Service
public class ProyectoServices {

	private ProyectoRepository proyectoRepository;
	private UsuarioServices usuarioServices;

	@Autowired
	public ProyectoServices(ProyectoRepository proyectoRepository, UsuarioServices usuarioServices) {
		this.proyectoRepository = proyectoRepository;
		this.usuarioServices = usuarioServices;
	}

	public void crearProyecto(Proyecto proyecto){
		proyecto.setFechaInicioProyecto(new Date());
		proyecto.setFechaFinalProyecto(new Date());
		proyecto.setUsuario(usuarioServices.getUsuario());
		proyectoRepository.save(proyecto);
	}

	public Proyecto proyecto(String nombreProyecto) {
		try {
			Proyecto proyecto = proyectoRepository.findByNombreProyecto(nombreProyecto);
			if (usuarioServices.getUsuario().equals(proyecto.getUsuario())) {
				return proyecto;
			}
			throw new NotFoundExcepcion("Este Proyecto no te pertenece");
		} catch (NullPointerException e) {
			throw new BadRequestException("Este Proyecto no existe..");
		}
	}
}
