package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.CategoriaBoletoPromocion;


@Repository
public interface CategoriaBoletoPromocionRepository extends JpaRepository<CategoriaBoletoPromocion,Integer>{

}
