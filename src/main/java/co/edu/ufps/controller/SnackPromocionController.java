package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.SnackPromocion;
import co.edu.ufps.services.SnackPromocionService;

@RestController
@RequestMapping("/SnackPromocion")
public class SnackPromocionController {

    @Autowired
    private SnackPromocionService snackPromocionService;

    @GetMapping
    public List<SnackPromocion> getAll() {
        return snackPromocionService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SnackPromocion> getById(@PathVariable Integer id) {
        Optional<SnackPromocion> snackPromocionOpt = snackPromocionService.getById(id);
        return snackPromocionOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SnackPromocion> create(@RequestBody SnackPromocion snackPromocion) {
        SnackPromocion createdPromocion = snackPromocionService.create(snackPromocion);
        return ResponseEntity.status(201).body(createdPromocion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SnackPromocion> update(@PathVariable Integer id,
            @RequestBody SnackPromocion snackPromocion) {
        Optional<SnackPromocion> updatedPromocion = snackPromocionService.update(id, snackPromocion);
        return updatedPromocion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
