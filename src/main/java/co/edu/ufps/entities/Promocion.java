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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="promocion")
@Data
public class Promocion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
		
	@Column(name="descripcion", length=100)
	private String descripcion;

	@Column(name="fecha_inicio")
	private Date fechaInicio;
	
	@Column(name="fecha_fin")
	private Date fechaFin;
	
	@OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CategoriaBoletoPromocion> categoriaBoletoPromociones;
	
	@OneToMany(mappedBy = "promo", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SnackPromocion> snackPromociones;
	
	@OneToMany(mappedBy = "prom", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PedidoBoletoPromocion> pedidoBoletoPromociones;
	
	@OneToMany(mappedBy = "promSnack", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PedidoSnackPromocion> pedidoSnackPromociones;
}
