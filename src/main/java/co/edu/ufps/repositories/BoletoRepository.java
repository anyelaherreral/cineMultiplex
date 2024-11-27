package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.Funcion;


@Repository
public interface BoletoRepository extends JpaRepository<Boleto,Integer>{

	@Query("SELECT COUNT(b) > 0 FROM Boleto b WHERE b.funcion.id = :funcionId AND b.asiento.id = :asientoId")
	boolean existsByFuncionIdAndAsientoId(@Param("funcionId") Integer funcionId, @Param("asientoId") Integer asientoId);

	@Query("SELECT b.asiento FROM Boleto b WHERE b.funcion.id = :funcionId")
	List<Asiento> findAsientosByFuncionId(@Param("funcionId") Integer funcionId);
}
