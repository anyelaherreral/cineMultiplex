package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.SnackPromocion;



@Repository
public interface SnackPromocionRepository extends JpaRepository<SnackPromocion,Integer>{

}
