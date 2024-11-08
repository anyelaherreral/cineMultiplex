package co.edu.ufps.entities;



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
@Table(name="pedido_boleto_promocion")
@Data
public class PedidoBoletoPromocion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", unique = true)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "promocion_id", unique = true)
    private Promocion prom;
    
    @OneToOne
    @JoinColumn(name = "boleto_id", unique = true) // La columna de clave foránea en Empleado
    private Boleto boleto;

    
}
