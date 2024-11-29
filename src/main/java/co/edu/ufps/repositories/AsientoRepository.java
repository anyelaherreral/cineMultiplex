package co.edu.ufps.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Asiento;


@Repository
public interface AsientoRepository extends JpaRepository<Asiento,Integer>{
	
	Optional<Asiento> findBySalaIdAndLetraAndNumeroAsiento(Integer salaId,String letra,String numeroAsiento);
	
	List<Asiento> findBySalaIdAndEstadoDescripcion(Integer salaId, String estadoDescripcion);

	
	 Optional<Asiento> findBySalaIdAndLetraAndNumeroAsientoAndEstadoDescripcion(Integer salaId, String letra, String numeroAsiento, String estadoDescripcion);

	List<Asiento> findBySalaId(Integer salaId);


}