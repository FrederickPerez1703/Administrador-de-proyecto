package com.proyecto.services;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Proyecto;
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

	public void crearProyecto(Proyecto proyecto) {
		proyecto.setFechaInicioProyecto(new Date());
		proyecto.setFechaFinalProyecto(new Date());
		proyecto.setUsuario(usuarioServices.getUsuario());
		proyectoRepository.save(proyecto);
	}

	public Proyecto proyecto(String nombreProyecto) {
		try {
			Optional<Proyecto> optionalProyecto = proyectoRepository.findByNombreProyecto(nombreProyecto);
			if (usuarioServices.getUsuario().equals(optionalProyecto.get().getUsuario())) {
				return optionalProyecto.get();
			}
			throw new NotFoundExcepcion("Este Proyecto no le Pertenece");
		} catch (NoSuchElementException e) {
			throw new NotFoundExcepcion("Este Proyecto no Existe");
		}
	}
}
