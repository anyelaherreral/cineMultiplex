package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer>{

}
