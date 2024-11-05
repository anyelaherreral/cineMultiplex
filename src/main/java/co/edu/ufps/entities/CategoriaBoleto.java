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
@Table(name="categoria_boleto")
@Data
public class CategoriaBoleto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="descripcion", length=50)
	private String descripcion;
	
	@Column(name="precio")
	private Float precio;
	
	@OneToMany(mappedBy = "categoriaBoleto", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Boleto> boletos;
	
	@OneToMany(mappedBy = "categoriaBoleto", cascade = CascadeType.ALL)
	private List<CategoriaBoletoPromocion> categoriaBoletoPromocion;
	
//	@ManyToMany
//	@JoinTable(
//			name ="categoria_boleto_promocion",
//			joinColumns = @JoinColumn(name="categoria_boleto_id"),
//			inverseJoinColumns = @JoinColumn(name="promocion_id")
//			)
//	List<Promocion> promociones;
}
