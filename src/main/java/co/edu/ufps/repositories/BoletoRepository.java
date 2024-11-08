package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Boleto;


@Repository
public interface BoletoRepository extends JpaRepository<Boleto,Integer>{

}
