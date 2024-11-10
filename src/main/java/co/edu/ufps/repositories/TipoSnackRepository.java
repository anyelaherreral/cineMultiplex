package co.edu.ufps.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.TipoSnack;


@Repository
public interface TipoSnackRepository extends JpaRepository<TipoSnack,Integer>{
	List<TipoSnack> findByDescripcion(String descripcion);
}
