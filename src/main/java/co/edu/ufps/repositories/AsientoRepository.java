package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Asiento;


@Repository
public interface AsientoRepository extends JpaRepository<Asiento,Integer>{
//	List<Asiento> findBySalaId(Integer salaId);
}