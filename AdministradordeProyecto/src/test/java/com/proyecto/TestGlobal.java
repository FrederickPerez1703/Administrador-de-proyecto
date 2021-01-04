package com.proyecto;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.proyecto.controller.RestAdministradorProyecto;
import com.proyecto.controller.RestAutenticacion;
import com.proyecto.entidad.Persona;
import com.proyecto.entidad.Proyecto;
import com.proyecto.entidad.Usuario;
import com.proyecto.services.ProyectoServices;
import com.proyecto.services.ValidacionService;

@SpringBootTest
public class TestGlobal {
	@Mock
	ProyectoServices proyectoServices;
	@Mock
	Proyecto proyecto;
	@Mock
	Usuario usuario;
	@Mock
	Persona persona;
	@Mock
	ValidacionService validacion;

	@InjectMocks
	RestAdministradorProyecto restAdministradorProyecto;

	@InjectMocks
	RestAutenticacion restAutenticacion;

	@BeforeEach
	void testProyecto() {

		Persona mockPersona = new Persona();
		mockPersona.setId(53L);
		mockPersona.setNombre("prueba");
		mockPersona.setApellido("Prueba");
		mockPersona.setEmail("Prueba@Gamil.com");
		mockPersona.setPassword("$2a$10$vQfKvZ3QsDgxDpqn");
		mockPersona.setUsuario("Prueba");
		Mockito.when(persona.toString()).thenReturn(mockPersona.toString());

		Usuario mockUsuario = new Usuario();
		mockUsuario.setPassword("$2a$10$vQfKvZ3QsDgxDpqn.otpB.ECIjL5xOKgoJJqUf2A1Hcf.CVkU.yY2");
		mockUsuario.setUsuario("Prueba");
		mockUsuario.setRol("USER");
		mockUsuario.setPersona(mockPersona);
		mockUsuario.setId(53L);
		Mockito.when(usuario.toString()).thenReturn(mockUsuario.toString());

		Proyecto mockProyecto = new Proyecto();
		mockProyecto.setId(1L);
		mockProyecto.setParticipante(4);
		mockProyecto.setComentario("Desarrolando una APP ");
		mockProyecto.setFechaFinalProyecto(new Date());
		mockProyecto.setFechaInicioProyecto(new Date());
		mockProyecto.setNombreProyecto("Prueba");
		mockProyecto.setUsuario(mockUsuario);
		mockProyecto.setNombreProyecto("Developer");
		Mockito.when(proyecto.toString()).thenReturn(mockProyecto.toString());

	}

	@Test
	void pruebaCrearProyecto() {
		restAdministradorProyecto.crearProyecto(proyecto);
	}

	@Test
	void buscarProyecto() {
		System.out.print(restAdministradorProyecto.proyecto("prueba"));
	}
}
