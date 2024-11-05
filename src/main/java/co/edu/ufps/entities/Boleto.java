package co.edu.ufps.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="boleto")
@Data
public class Boleto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="asiento_id", unique = true)
	private Asiento asiento;
	
	@ManyToOne
	@JoinColumn(name="funcion_id", unique = true)
	private Funcion funcion;
	
	@ManyToOne
	@JoinColumn(name="categoria_boleto_id")
	private CategoriaBoleto categoriaBoleto;
	
	@OneToOne(mappedBy = "boleto")
    private PedidoBoletoPromocion pedidoBoletoPromociones;
}
