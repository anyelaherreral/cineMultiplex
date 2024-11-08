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
@Table(name="rol")
@Data
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="descripcion", length=100, unique=true)
	private String descripcion;
	
	@OneToMany(mappedBy = "rol", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Empleado> empleados;

	public void addEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
	}

	public void removeEmpleado(Empleado empleado) {
		this.empleados.remove(empleado);
		
	}
	
	
	
}
