package com.proyecto.excepciones;

public class NotFoundExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundExcepcion(String msj) {
		super(msj);
	}
}
