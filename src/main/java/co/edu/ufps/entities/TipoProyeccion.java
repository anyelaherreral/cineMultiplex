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
@Table(name="tipo_proyeccion")
@Data
public class TipoProyeccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="descripcion", length=50)
	private String descripcion;
	
	@OneToMany(mappedBy = "tipoProyeccion", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Sala> salas;
	
	public void addSala(Sala sala) {
		this.salas.add(sala);
	}

	public void removeSala(Sala sala) {
		this.salas.remove(sala);
		
	}
}
