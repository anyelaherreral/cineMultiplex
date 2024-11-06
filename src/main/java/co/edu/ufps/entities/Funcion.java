package co.edu.ufps.entities;



import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="funcion")
@Data
public class Funcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="horario")
	private Time horario;
	
	@Column(name="fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="pelicula_id")
	private Pelicula pelicula;
	
	@ManyToOne
	@JoinColumn(name="sala_id")
	private Sala sala;
	
	@ManyToMany(mappedBy = "funciones")
	@JsonIgnore
	private List<Empleado> empleados;
	
	@OneToMany(mappedBy = "funcion", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Boleto> boletos;
	
	
}
