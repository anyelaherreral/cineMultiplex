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

    @GetMapping("/{id}")
    public ResponseEntity<Asiento> getById(@PathVariable Integer id) {
        Optional<Asiento> asiento = asientoService.getById(id);
        return asiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asiento> update(@PathVariable Integer id, @RequestBody Asiento asientoDetails) {
        Optional<Asiento> updatedAsiento = asientoService.update(id, asientoDetails);
        return updatedAsiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = asientoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

//    /**
//     * Asocia un boleto a un asiento.
//     *
//     * @param id ID del asiento.
//     * @param boletoId ID del boleto a asociar.
//     * @return El asiento actualizado con el boleto asociado.
//     */
//    @PostMapping("/{id}/boletos/{boletoId}")
//    public ResponseEntity<Asiento> addBoleto(@PathVariable Integer id, @PathVariable Integer boletoId) {
//        Asiento updatedAsiento = asientoService.addBoleto(id, boletoId);
//        return updatedAsiento != null ? ResponseEntity.ok(updatedAsiento) : ResponseEntity.notFound().build();
//    }

//    /**
//     * Busca todos los asientos de una sala específica.
//     *
//     * @param salaId ID de la sala.
//     * @return Lista de asientos en la sala.
//     */
//    @GetMapping("/sala/{salaId}")
//    public List<Asiento> findBySala(@PathVariable Integer salaId) {
//        return asientoService.findBySala(salaId);
//    }


    @PutMapping("/estado")
    public List<Asiento> updateEstadoMultiple(@RequestBody List<Integer> ids, @RequestBody String nuevoEstado) {
        return asientoService.updateEstadoMultiple(ids, nuevoEstado);
    }

    @GetMapping("/{id}/disponible")
    public ResponseEntity<Boolean> isAvailable(@PathVariable Integer id) {
        boolean available = asientoService.isAvailable(id);
        return ResponseEntity.ok(available);
    }

    @PutMapping("/{id}/liberar")
    public ResponseEntity<Asiento> releaseAsiento(@PathVariable Integer id) {
        Optional<Asiento> releasedAsiento = asientoService.releaseAsiento(id);
        return releasedAsiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   
}