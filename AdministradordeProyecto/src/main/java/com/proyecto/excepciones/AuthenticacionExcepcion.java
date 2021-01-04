package com.proyecto.excepciones;

public class AuthenticacionExcepcion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AuthenticacionExcepcion(String msj) {
		super(msj);
	}
}
