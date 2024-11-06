package co.edu.ufps.repositories;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ufps.entities.Promocion;

public interface PromocionRepository extends JpaRepository<Promocion,Integer> {
	// Encontrar funciones por fecha
	List<Promocion> findByFechaInicio(Date fechaInicio);

	// Encontrar funciones por horario
	List<Promocion> findByFechaFin(Date fechaFin);
	
	List<Promocion> findByDescripcion(String descripcion);
}
