package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.CategoriaBoleto;


@Repository
public interface CategoriaBoletoRepository extends JpaRepository<CategoriaBoleto,Integer>{

}
