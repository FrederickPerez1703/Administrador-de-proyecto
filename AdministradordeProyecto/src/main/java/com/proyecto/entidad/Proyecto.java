package com.proyecto.entidad;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@Entity
@Table(name = "proyecto")
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre_proyecto")
	private String nombreProyecto;
	@Column(name = "Participante")
	private int participante;
	@Column(name = "fecha_inicio_proyecto")
	private Date fechaInicioProyecto;
	@Column(name = "fecha_final_proyecto")
	private Date fechaFinalProyecto;
	@Column(name = "Comentario")
	private String comentario;
	@OneToOne
	@JoinColumn(name = "Usuario", referencedColumnName = "id")
	private Usuario usuario;
}
