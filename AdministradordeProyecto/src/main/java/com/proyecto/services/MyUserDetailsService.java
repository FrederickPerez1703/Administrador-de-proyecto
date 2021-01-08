package com.proyecto.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Usuario;
import com.proyecto.repository.UsuarioRepository;
import com.proyecto.security.UsuarioPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usu = usuarioRepository.findByUsuario(username);
		if (usu == null) {
			throw new UsernameNotFoundException("Este Usuario no esta Registrado");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usu.getRol()));
		UsuarioPrincipal usuario = new UsuarioPrincipal(usu.getUsuario(), usu.getPassword(), roles);
		return usuario;
	}
}
