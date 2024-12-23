package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.CategoriaBoleto;
import co.edu.ufps.entities.Snack;
import co.edu.ufps.services.CategoriaBoletoService;
import co.edu.ufps.services.SnackService;

@RestController
@RequestMapping("/snacks")
public class SnackController {

	@Autowired
    private SnackService snackService;

	
    @GetMapping
    public List<Snack> list() {
        return snackService.list();
    }

    @PostMapping
    public ResponseEntity<Snack > create(@RequestBody Snack snack) {
        
        return ResponseEntity.ok(snackService.create(snack));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Snack> getById(@PathVariable String id) {
    	Optional<Snack> snack = snackService.getByDocumento(id);
        return snack.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Snack> update(@PathVariable String id, @RequestBody Snack snackDetails) {
        Optional<Snack> updatedSnack = snackService.update(id, snackDetails);
        return updatedSnack.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = snackService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
 
    @PostMapping("/{id}/add_snack_promocion/{snackPromocionId}")
    public ResponseEntity<Snack> addSnackPromocion(@PathVariable String id, @PathVariable Integer snackPromocionId) {
        Snack updatedSnack = snackService.addSnackPromocion(id, snackPromocionId);
        return updatedSnack != null ? ResponseEntity.ok(updatedSnack) : ResponseEntity.notFound().build();
    }
 
    @PostMapping("/{id}/add_pedido_snack_promocion/{pedidoSnackPromocionId}")
    public ResponseEntity<Snack> addPedidoSnackPromocion(@PathVariable String id, @PathVariable Integer pedidoSnackPromocionId) {
        Snack updatedSnack = snackService.addPedidoSnackPromocion(id, pedidoSnackPromocionId);
        return updatedSnack != null ? ResponseEntity.ok(updatedSnack) : ResponseEntity.notFound().build();
    }
    
    @PatchMapping("/{id}/restar/{cantidad}")
    public ResponseEntity<Snack> restarCantidadDisponible(
            @PathVariable("id") String id,
            @PathVariable("cantidad") Integer cantidadRestar) {

        try { 
            Snack snackActualizado = snackService.restarCantidadDisponible(id, cantidadRestar);
 
            if (snackActualizado == null) {
                return ResponseEntity.notFound().build();
            }
 
            return ResponseEntity.ok(snackActualizado);
        } catch (IllegalArgumentException e) { 
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) { 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
