package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.PedidoSnackPromocion;


@Repository
public interface PedidoSnackPromocionRepository extends JpaRepository<PedidoSnackPromocion,Integer>{

}
