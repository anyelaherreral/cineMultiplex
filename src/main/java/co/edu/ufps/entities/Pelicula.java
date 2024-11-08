package co.edu.ufps.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pelicula")
@Data
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
		
	@Column(name="titulo", length=70)
	private String titulo;

	@Column(name="duracion", length=8)
	private String duracion;
	
	@Column(name="sinopsis", length=500)
	private String sinopsis;
	
	@ManyToOne
	@JoinColumn(name="genero_id")
	private Genero genero;
	
	@OneToMany(mappedBy = "pelicula", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Funcion> funciones;
	
}
