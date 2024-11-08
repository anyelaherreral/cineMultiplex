package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Sala;




@Repository
public interface SalaRepository extends JpaRepository<Sala,Integer>{

}
