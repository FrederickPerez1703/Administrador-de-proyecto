package com.proyecto.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Nombre")
	private String nombre; 
	@Column(name = "Apellido")
	private String apellido;
	@Column(name = "Email")
	private String email;
	@Column(name = "Usuario")
	private String usuario;
	@Column(name = "Password")
	@JsonIgnore
	private String password;
}
