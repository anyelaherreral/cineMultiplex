package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.MetodoPago;


@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago,Integer>{
//	/**
//     * Busca un método de pago por su descripción.
//     * @param descripcion La descripción del método de pago a buscar.
//     * @return Un Optional que contiene el MetodoPago si se encuentra, o vacío si no.
//     */
//    Optional<MetodoPago> findByDescripcion(String descripcion);
//
//    /**
//     * Elimina un método de pago basado en su descripción.
//     * @param descripcion La descripción del método de pago a eliminar.
//     * @return El número de entidades eliminadas (0 si no se encontró ninguna).
//     */
//    void deleteByDescripcion(String descripcion);

}
