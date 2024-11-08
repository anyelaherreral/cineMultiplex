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
@Table(name="estado")
@Data
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="descripcion", length=50)
	private String descripcion;
	
	@OneToMany(mappedBy = "estado", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Asiento> asientos;
	
	public void addAsiento(Asiento asiento) {
		this.asientos.add(asiento);
	}

	public void removeAsiento(Asiento asiento) {
		this.asientos.remove(asiento);
		
	}
}
