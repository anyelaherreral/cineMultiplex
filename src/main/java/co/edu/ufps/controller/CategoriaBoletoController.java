package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.CategoriaBoleto;
import co.edu.ufps.services.CategoriaBoletoService;

@RestController
@RequestMapping("/api/categorias-boletos")
public class CategoriaBoletoController {

    @Autowired
    private CategoriaBoletoService categoriaBoletoService;

    @GetMapping
    public ResponseEntity<List<CategoriaBoleto>> list() {
        List<CategoriaBoleto> categorias = categoriaBoletoService.list();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaBoleto> create(@RequestBody CategoriaBoleto categoriaBoleto) {
        CategoriaBoleto createdCategoria = categoriaBoletoService.create(categoriaBoleto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaBoleto> getById(@PathVariable Integer id) {
        Optional<CategoriaBoleto> categoriaBoletoOpt = categoriaBoletoService.getById(id);
        return categoriaBoletoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaBoleto> update(@PathVariable Integer id, @RequestBody CategoriaBoleto categoriaBoletoDetails) {
        Optional<CategoriaBoleto> updatedCategoria = categoriaBoletoService.update(id, categoriaBoletoDetails);
        return updatedCategoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = categoriaBoletoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoriaId}/boletos")
    public ResponseEntity<List<Boleto>> getBoletosByCategoria(@PathVariable Integer categoriaId) {
        List<Boleto> boletos = categoriaBoletoService.getBoletosByCategoria(categoriaId);
        return ResponseEntity.ok(boletos);
    }

    @PostMapping("/{categoriaId}/boletos")
    public ResponseEntity<CategoriaBoleto> addBoletoToCategoria(@PathVariable Integer categoriaId, @RequestBody Boleto boleto) {
        Optional<CategoriaBoleto> updatedCategoria = categoriaBoletoService.addBoletoToCategoria(categoriaId, boleto);
        return updatedCategoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
