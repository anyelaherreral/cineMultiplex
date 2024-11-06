package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.CategoriaBoletoPromocion;
import co.edu.ufps.repositories.CategoriaBoletoPromocionRepository;

@Service
public class CategoriaBoletoPromocionService {

    @Autowired
    private CategoriaBoletoPromocionRepository categoriaBoletoPromocionRepository;

    /**
     * Lista todas las promociones de categorías de boletos.
     * 
     * @return Lista de promociones de categorías de boletos.
     */
    public List<CategoriaBoletoPromocion> list() {
        return categoriaBoletoPromocionRepository.findAll();
    }

    /**
     * Crea una nueva promoción para una categoría de boleto.
     * 
     * @param categoriaBoletoPromocion La promoción a crear.
     * @return La promoción creada.
     */
    public CategoriaBoletoPromocion create(CategoriaBoletoPromocion categoriaBoletoPromocion) {
        return categoriaBoletoPromocionRepository.save(categoriaBoletoPromocion);
    }

    /**
     * Obtiene una promoción de categoría de boleto por su ID.
     * 
     * @param id ID de la promoción.
     * @return La promoción, si está presente.
     */
    public Optional<CategoriaBoletoPromocion> getById(Integer id) {
        return categoriaBoletoPromocionRepository.findById(id);
    }

    /**
     * Actualiza una promoción de categoría de boleto existente.
     * 
     * @param id                      ID de la promoción a actualizar.
     * @param categoriaBoletoPromocionDetails Detalles actualizados de la promoción.
     * @return La promoción actualizada, si está presente.
     */
    public Optional<CategoriaBoletoPromocion> update(Integer id, CategoriaBoletoPromocion categoriaBoletoPromocionDetails) {
        Optional<CategoriaBoletoPromocion> optionalPromocion = categoriaBoletoPromocionRepository.findById(id);
        if (!optionalPromocion.isPresent()) {
            return Optional.empty();
        }

        CategoriaBoletoPromocion categoriaBolProm = optionalPromocion.get();
        categoriaBolProm.setDescuento(categoriaBoletoPromocionDetails.getDescuento());
        categoriaBolProm.setCategoriaBoleto(categoriaBoletoPromocionDetails.getCategoriaBoleto());
        categoriaBolProm.setPromocion(categoriaBoletoPromocionDetails.getPromocion());

        return Optional.of(categoriaBoletoPromocionRepository.save(categoriaBolProm));
    }

}
