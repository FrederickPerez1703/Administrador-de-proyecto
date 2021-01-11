package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.entidad.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	 Usuario findByUsuario(String usuario);
}
