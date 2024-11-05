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
@Table(name="sala")
@Data
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="num_asientos")
	private Integer num_asientos;
	
	@ManyToOne
	@JoinColumn(name="tipo_proyeccion_id")
	private TipoProyeccion tipoProyeccion;
	
	@OneToMany(mappedBy = "sala", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Funcion> funciones;
}
