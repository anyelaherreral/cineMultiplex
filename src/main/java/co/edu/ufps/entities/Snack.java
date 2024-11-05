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
@Table(name="snack")
@Data
public class Snack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre", length=70)
	private String nombre;
	
	@Column(name="precio")
	private Float precio;
	
	@Column(name="cantidad_disponible")
	private Integer cantidadDisponible;
	
	@ManyToOne
	@JoinColumn(name="tipo_snack_id")
	private TipoSnack tipoSnack;
	
	@OneToMany(mappedBy = "snack", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SnackPromocion> snackPromociones;
	
	@OneToMany(mappedBy = "snacks", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PedidoSnackPromocion> pedidoSnackPromociones;
	
	public void addSnackPromocion(SnackPromocion snackPromocion) {
		this.snackPromociones.add(snackPromocion);
	}

	public void removeSnackPromocion(SnackPromocion snackPromocion) {
		this.snackPromociones.remove(snackPromocion);
		
	}
	
	public void addPedidoSnackPromocion(PedidoSnackPromocion pedidoSnackPromocion) {
		this.pedidoSnackPromociones.add(pedidoSnackPromocion);
	}

	public void removePedidoSnackPromocion(PedidoSnackPromocion pedidoSnackPromocion) {
		this.pedidoSnackPromociones.remove(pedidoSnackPromocion);
		
	}
}
