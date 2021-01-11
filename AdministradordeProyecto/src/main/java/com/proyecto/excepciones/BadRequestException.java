package com.proyecto.excepciones;

import org.springframework.http.ResponseEntity;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BadRequestException(ResponseEntity<?> httpStatus) {
		super(httpStatus.toString());
	}
	public BadRequestException(String msj ) {
		super(msj);
	}
}
