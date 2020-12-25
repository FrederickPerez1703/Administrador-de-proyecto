package com.proyecto.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.excepciones.BadRequestException;

@ControllerAdvice
public class ExceptionConfig {
	
	@ResponseBody
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badResquesExceprion(Exception e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
	}
}
