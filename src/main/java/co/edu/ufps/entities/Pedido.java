package co.edu.ufps.entities;


import java.util.Date;
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
@Table(name="pedido")
@Data
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // El 'id' será generado automáticamente
	@Column(name="id")
	private Integer id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="total")
	private Float total;
	
	@ManyToOne 
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne 
	@JoinColumn(name="metodo_pago_id")
	private MetodoPago metodoPago;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PedidoBoletoPromocion> pedidoBoletoPromociones;
	
	@OneToMany(mappedBy = "pedidoSnack", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PedidoSnackPromocion> pedidoSnackPromociones;
	
	
}
