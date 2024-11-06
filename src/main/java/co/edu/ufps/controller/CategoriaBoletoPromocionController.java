package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.CategoriaBoletoPromocion;
import co.edu.ufps.services.CategoriaBoletoPromocionService;

@RestController
@RequestMapping("/api/categoria-boleto-promocion")
public class CategoriaBoletoPromocionController {

    @Autowired
    private CategoriaBoletoPromocionService categoriaBoletoPromocionService;

    /**
     * Obtiene todas las promociones de categorías de boletos.
     * 
     * @return Lista de promociones.
     */
    @GetMapping
    public List<CategoriaBoletoPromocion> getAll() {
        return categoriaBoletoPromocionService.list();
    }

    /**
     * Obtiene una promoción de categoría de boleto por su ID.
     * 
     * @param id ID de la promoción.
     * @return Promoción si se encuentra, o not found si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaBoletoPromocion> getById(@PathVariable Integer id) {
        Optional<CategoriaBoletoPromocion> categoriaBoletoPromocionOpt = categoriaBoletoPromocionService.getById(id);
        return categoriaBoletoPromocionOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva promoción de categoría de boleto.
     * 
     * @param categoriaBoletoPromocion La promoción a crear.
     * @return La promoción creada.
     */
    @PostMapping
    public ResponseEntity<CategoriaBoletoPromocion> create(@RequestBody CategoriaBoletoPromocion categoriaBoletoPromocion) {
        CategoriaBoletoPromocion createdPromocion = categoriaBoletoPromocionService.create(categoriaBoletoPromocion);
        return ResponseEntity.status(201).body(createdPromocion);
    }

    /**
     * Actualiza una promoción de categoría de boleto existente.
     * 
     * @param id ID de la promoción a actualizar.
     * @param categoriaBoletoPromocion Detalles actualizados de la promoción.
     * @return La promoción actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaBoletoPromocion> update(@PathVariable Integer id,
            @RequestBody CategoriaBoletoPromocion categoriaBoletoPromocion) {
        Optional<CategoriaBoletoPromocion> updatedPromocion = categoriaBoletoPromocionService.update(id, categoriaBoletoPromocion);
        return updatedPromocion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
