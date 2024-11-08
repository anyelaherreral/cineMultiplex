package co.edu.ufps.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entities.Funcion;


@Repository
public interface FuncionRepository extends JpaRepository<Funcion,Integer>{
	List<Funcion> findByFecha(LocalDate fecha);

//	// Encontrar funciones por fecha
//    List<Funcion> findByFecha(LocalDate fecha);
//
//    // Encontrar funciones por horario
//    List<Funcion> findByHorario(LocalTime horario);
    
}
