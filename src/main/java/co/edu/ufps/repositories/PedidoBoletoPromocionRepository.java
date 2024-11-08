package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.PedidoBoletoPromocion;


@Repository
public interface PedidoBoletoPromocionRepository extends JpaRepository<PedidoBoletoPromocion,Integer>{

}
