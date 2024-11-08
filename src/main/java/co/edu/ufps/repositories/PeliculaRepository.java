package co.edu.ufps.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entities.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Integer>{
	Optional<Pelicula> findByTitulo(String titulo);
	
}
