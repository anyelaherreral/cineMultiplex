package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.PedidoSnackPromocion;
import co.edu.ufps.services.PedidoSnackPromocionService;

@RestController
@RequestMapping("/api/pedido-snack-promocion")
public class PedidoSnackPromocionController {

    @Autowired
    private PedidoSnackPromocionService pedidoSnackPromocionService;

    /**
     * Obtiene todos los PedidoSnackPromocion.
     * 
     * @return Lista de PedidoSnackPromocion.
     */
    @GetMapping
    public List<PedidoSnackPromocion> getAll() {
        return pedidoSnackPromocionService.list();
    }

    /**
     * Obtiene un PedidoSnackPromocion por su ID.
     * 
     * @param id ID del PedidoSnackPromocion.
     * @return ResponseEntity con el PedidoSnackPromocion encontrado o Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PedidoSnackPromocion> getById(@PathVariable Integer id) {
        Optional<PedidoSnackPromocion> pedidoSnackPromocionOpt = pedidoSnackPromocionService.getById(id);
        return pedidoSnackPromocionOpt.map(ResponseEntity::ok)
                                       .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Crea un nuevo PedidoSnackPromocion.
     * 
     * @param pedidoSnackPromocion El PedidoSnackPromocion a crear.
     * @return ResponseEntity con el PedidoSnackPromocion creado.
     */
    @PostMapping
    public ResponseEntity<PedidoSnackPromocion> create(@RequestBody PedidoSnackPromocion pedidoSnackPromocion) {
        PedidoSnackPromocion created = pedidoSnackPromocionService.create(pedidoSnackPromocion);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Actualiza un PedidoSnackPromocion existente.
     * 
     * @param id                   ID del PedidoSnackPromocion a actualizar.
     * @param pedidoSnackPromocion Detalles actualizados del PedidoSnackPromocion.
     * @return ResponseEntity con el PedidoSnackPromocion actualizado o Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PedidoSnackPromocion> update(@PathVariable Integer id,
                                                       @RequestBody PedidoSnackPromocion pedidoSnackPromocion) {
        Optional<PedidoSnackPromocion> updated = pedidoSnackPromocionService.update(id, pedidoSnackPromocion);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Elimina un PedidoSnackPromocion por su ID.
     * 
     * @param id ID del PedidoSnackPromocion a eliminar.
     * @return ResponseEntity con el estado de la eliminación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = pedidoSnackPromocionService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
