package com.proyecto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import com.proyecto.entidad.Persona;
import com.proyecto.services.ValidacionService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class AdministradordeProyectoApplicationTests {
	
	@Autowired
	private ValidacionService validacionService; 
	
	
	@Test
	void contextLoads() {
		
		Persona p = new Persona();
		p.setNombre("p ");
		p.setApellido("p ");
		p.setEmail("juan ");
		p.setUsuario(" a ");
		p.setPassword("a");
		System.out.print(p);
		Assert.isTrue(validacionService.isValidarPersona(p));;
	}

}
