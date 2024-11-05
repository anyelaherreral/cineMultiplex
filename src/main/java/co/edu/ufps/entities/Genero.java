package co.edu.ufps.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="genero")
@Data
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="descripcion", length=50)
	private String descripcion;
	
	@OneToMany(mappedBy = "genero", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Pelicula> peliculas;

	public void addPelicula(Pelicula pelicula) {
		this.peliculas.add(pelicula);
	}

	public void removePelicula(Pelicula pelicula) {
		this.peliculas.remove(pelicula);
		
	}
}
