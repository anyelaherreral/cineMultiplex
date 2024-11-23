package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.services.AsientoService;

@RestController
@RequestMapping("/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    @GetMapping
    public List<Asiento> list() {
        return asientoService.list();
    }

    @PostMapping
    public Asiento create(@RequestBody Asiento asiento) {
        return asientoService.create(asiento);
    }

    @GetMapping("/sala/{salaId}/letra/{letra}/numero/{numeroAsiento}")
    public ResponseEntity<Asiento> getAsientoBySalaAndLetraAndNumero(
            @PathVariable Integer salaId,
            @PathVariable String letra,
            @PathVariable String numeroAsiento) {

        Optional<Asiento> asientoOpt = asientoService.getAsiento(salaId, letra, numeroAsiento);
        return asientoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{salaId}/{letra}/{numeroAsiento}")
    public ResponseEntity<Asiento> updateAsiento(@PathVariable Integer salaId,
                                                 @PathVariable String letra,
                                                 @PathVariable String numeroAsiento,
                                                 @RequestBody Asiento asientoDetails) {
        Optional<Asiento> updatedAsiento = asientoService.update(salaId, letra, numeroAsiento, asientoDetails);
        return updatedAsiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/sala/{salaId}/letra/{letra}/numero/{numeroAsiento}")
    public ResponseEntity<Void> deleteAsiento(@PathVariable Integer salaId,
                                              @PathVariable String letra, 
                                              @PathVariable String numeroAsiento) {
        // Llamar al servicio para eliminar el asiento
        boolean deleted = asientoService.deleteBySalaLetraNumero(salaId, letra, numeroAsiento);
        
        if (deleted) {
            return ResponseEntity.noContent().build(); // Devuelve un 204 No Content si se eliminó correctamente
        } else {
            return ResponseEntity.notFound().build(); // Devuelve un 404 Not Found si no se encontró el asiento
        }
    }

    @GetMapping("/sala/{salaId}/letra/{letra}/numero/{numeroAsiento}/disponible")
    public ResponseEntity<Boolean> isAvailable(@PathVariable Integer salaId,
                                               @PathVariable String letra, 
                                               @PathVariable String numeroAsiento) {
        // Llamar al servicio para verificar si el asiento está disponible en la sala
        boolean available = asientoService.isAvailable(salaId, letra, numeroAsiento);

        return ResponseEntity.ok(available); // Devuelve 'true' o 'false' dependiendo de la disponibilidad
    }

    
    @GetMapping("/sala/{salaId}")
    public List<Asiento> getAsientosBySalaId(@PathVariable Integer salaId) {
        return asientoService.findBySalaId(salaId);
    }
    
}