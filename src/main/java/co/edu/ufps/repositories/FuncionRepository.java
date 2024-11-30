package co.edu.ufps.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entities.Funcion;


@Repository
public interface FuncionRepository extends JpaRepository<Funcion,Integer>{
	List<Funcion> findByFecha(LocalDate fecha);
	
	@Query("SELECT DISTINCT f.fecha FROM Funcion f")
    List<LocalDate> obtenerFechasDisponibles();
	
	boolean existsByPeliculaId(Integer peliculaId);
	
	@Query(value = "SELECT EXISTS (SELECT 1 FROM empleado_funcion ef WHERE ef.empleado_id = :empleadoId)", nativeQuery = true)
	boolean existsEmpleadoInFunciones(@Param("empleadoId") Integer empleadoId);
}
