package co.edu.ufps.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pedido_snack_promocion")
@Data
public class PedidoSnackPromocion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@ManyToOne
    @JoinColumn(name = "promocion_id", unique = true)
    private Promocion promSnack;
	
    @ManyToOne
    @JoinColumn(name = "pedido_id", unique = true)
    private Pedido pedidoSnack;
    
    @ManyToOne
    @JoinColumn(name = "pedido_snack_id", unique = true)
    private Snack snacks;

    

    
}
