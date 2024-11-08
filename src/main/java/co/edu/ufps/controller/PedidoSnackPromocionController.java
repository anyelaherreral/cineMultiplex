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


    @GetMapping
    public List<PedidoSnackPromocion> getAll() {
        return pedidoSnackPromocionService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoSnackPromocion> getById(@PathVariable Integer id) {
        Optional<PedidoSnackPromocion> pedidoSnackPromocionOpt = pedidoSnackPromocionService.getById(id);
        return pedidoSnackPromocionOpt.map(ResponseEntity::ok)
                                       .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<PedidoSnackPromocion> create(@RequestBody PedidoSnackPromocion pedidoSnackPromocion) {
        PedidoSnackPromocion created = pedidoSnackPromocionService.create(pedidoSnackPromocion);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoSnackPromocion> update(@PathVariable Integer id,
                                                       @RequestBody PedidoSnackPromocion pedidoSnackPromocion) {
        Optional<PedidoSnackPromocion> updated = pedidoSnackPromocionService.update(id, pedidoSnackPromocion);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = pedidoSnackPromocionService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
