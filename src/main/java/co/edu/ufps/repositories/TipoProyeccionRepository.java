package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.TipoProyeccion;


@Repository
public interface TipoProyeccionRepository extends JpaRepository<TipoProyeccion,Integer>{
//	Optional<TipoProyeccion> findByDescripcion(String descripcion);
}
