package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.MetodoPago;
import co.edu.ufps.entities.Rol;


@Repository
public interface RolRepository extends JpaRepository<Rol,Integer>{
	Optional<Rol> findByDescripcion(String descripcion);
}
