package co.edu.ufps.entities;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="empleado")
@Data
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // El 'id' será generado automáticamente
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre", length = 200)
	private String nombre;
	
	@Column(name="documento", length = 20, unique = true)
	private String documento;
	
	@Column(name="email", length = 200)
	private String email;
	
	@ManyToOne 
	@JoinColumn(name="rol_id")
	private Rol rol;
	
	@ManyToMany
	@JoinTable(
			name ="empleado_funcion",
			joinColumns = @JoinColumn(name="empleado_id"),
			inverseJoinColumns = @JoinColumn(name="funcion_id")
			)
	List<Funcion> funciones;
	
	
	public void addFuncion(Funcion funcion) {
		this.funciones.add(funcion);
	}

	public void removeFuncion(Funcion funcion) {
		this.funciones.remove(funcion);
	}
	
}
