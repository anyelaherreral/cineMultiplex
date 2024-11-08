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
@Table(name="cliente")
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre", length=200)
	private String nombre;
	
	@Column(name="telefono", length=20)
	private String telefono;
	
	@Column(name="email", length=200)
	private String email;
	
	
	@OneToMany(mappedBy = "cliente", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Pedido> pedidos;
}
