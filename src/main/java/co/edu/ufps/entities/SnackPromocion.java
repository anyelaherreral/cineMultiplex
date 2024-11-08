package co.edu.ufps.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="snack_promocion")
@Data
public class SnackPromocion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "descuento")
    private Float descuento;

    @ManyToOne
    @JoinColumn(name = "snack_promocion_id", unique = true)
    private Snack snack;

    @ManyToOne
    @JoinColumn(name = "promocion_id", unique = true)
    private Promocion promo;

    
}
