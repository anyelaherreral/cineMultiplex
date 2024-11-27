package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Sala;


@Repository
public interface SalaRepository extends JpaRepository<Sala,Integer>{
	
}
