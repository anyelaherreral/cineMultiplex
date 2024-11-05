package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Rol;


@Repository
public interface RolRepository extends JpaRepository<Rol,Integer>{

}
