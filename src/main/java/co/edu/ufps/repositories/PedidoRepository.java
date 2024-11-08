package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{

}
