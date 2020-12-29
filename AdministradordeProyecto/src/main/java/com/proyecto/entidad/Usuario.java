package com.proyecto.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(name= "Usuario")
	private String usuario;
	@Column(name = "Password")
	private String password; 
	@OneToOne
	@JoinColumn(name = "Persona" , referencedColumnName = "id")
	private Persona persona;
	private String rol; 
}
