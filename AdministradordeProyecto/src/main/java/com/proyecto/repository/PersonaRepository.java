package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.entidad.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	Persona findByEmail(String email);
	Persona findByUsuario(String usuario);
}
