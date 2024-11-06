package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Genero;
import co.edu.ufps.entities.MetodoPago;



@Repository
public interface GeneroRepository extends JpaRepository<Genero,Integer>{
	Optional<Genero> findByDescripcion(String descripcion);
}
