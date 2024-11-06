package co.edu.ufps.repositories;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Funcion;


@Repository
public interface FuncionRepository extends JpaRepository<Funcion,Integer>{

	// Encontrar funciones por fecha
    List<Funcion> findByFecha(Date fecha);

    // Encontrar funciones por horario
    List<Funcion> findByHorario(Time horario);
    
}
