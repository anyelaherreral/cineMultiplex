package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Genero;



@Repository
public interface GeneroRepository extends JpaRepository<Genero,Integer>{

}
