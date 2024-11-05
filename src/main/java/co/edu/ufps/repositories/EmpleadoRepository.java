package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer>{

}
