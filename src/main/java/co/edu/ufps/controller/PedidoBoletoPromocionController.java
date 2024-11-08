package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.PedidoBoletoPromocion;
import co.edu.ufps.services.PedidoBoletoPromocionService;

@RestController
@RequestMapping("/api/pedido-boleto-promocion")
public class PedidoBoletoPromocionController {

    @Autowired
    private PedidoBoletoPromocionService pedidoBoletoPromocionService;

    @GetMapping
    public List<PedidoBoletoPromocion> getAll() {
        return pedidoBoletoPromocionService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoBoletoPromocion> getById(@PathVariable Integer id) {
        Optional<PedidoBoletoPromocion> pedidoSnackPromocionOpt = pedidoBoletoPromocionService.getById(id);
        return pedidoSnackPromocionOpt.map(ResponseEntity::ok)
                                       .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping
    public ResponseEntity<PedidoBoletoPromocion> create(@RequestBody PedidoBoletoPromocion pedidoSnackPromocion) {
        PedidoBoletoPromocion created = pedidoBoletoPromocionService.create(pedidoSnackPromocion);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PedidoBoletoPromocion> update(@PathVariable Integer id,
                                                       @RequestBody PedidoBoletoPromocion pedidoSnackPromocion) {
        Optional<PedidoBoletoPromocion> updated = pedidoBoletoPromocionService.update(id, pedidoSnackPromocion);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = pedidoBoletoPromocionService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}