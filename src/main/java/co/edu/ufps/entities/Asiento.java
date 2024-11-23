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
@Table(name="asiento")
@Data
public class Asiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	

	@Column(name="letra", length =1, nullable = false)
	private String letra;
	@Column(name="numero_asiento", length =2, nullable = false)
	private String numeroAsiento;
	
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name="sala_id")
	private Sala sala;
	
	@OneToMany(mappedBy = "asiento", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Boleto> boletos;
	
	
	public void addBoleto(Boleto boleto) {
		this.boletos.add(boleto);
	}

	public void removeBoleto(Boleto boleto) {
		this.boletos.remove(boleto);
		
	}
}