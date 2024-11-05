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

    /**
     * Lista todas las categorías de boletos.
     * 
     * @return Lista de categorías de boletos.
     */
    @GetMapping
    public ResponseEntity<List<CategoriaBoleto>> list() {
        List<CategoriaBoleto> categorias = categoriaBoletoService.list();
        return ResponseEntity.ok(categorias);
    }

    /**
     * Crea una nueva categoría de boleto.
     * 
     * @param categoriaBoleto La categoría de boleto a crear.
     * @return La categoría de boleto creada.
     */
    @PostMapping
    public ResponseEntity<CategoriaBoleto> create(@RequestBody CategoriaBoleto categoriaBoleto) {
        CategoriaBoleto createdCategoria = categoriaBoletoService.create(categoriaBoleto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

    /**
     * Obtiene una categoría de boleto por su ID.
     * 
     * @param id ID de la categoría de boleto.
     * @return La categoría de boleto, si está presente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaBoleto> getById(@PathVariable Integer id) {
        Optional<CategoriaBoleto> categoriaBoletoOpt = categoriaBoletoService.getById(id);
        return categoriaBoletoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza una categoría de boleto existente.
     * 
     * @param id                  ID de la categoría de boleto a actualizar.
     * @param categoriaBoletoDetails Detalles actualizados de la categoría.
     * @return La categoría de boleto actualizada, si está presente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaBoleto> update(@PathVariable Integer id, @RequestBody CategoriaBoleto categoriaBoletoDetails) {
        Optional<CategoriaBoleto> updatedCategoria = categoriaBoletoService.update(id, categoriaBoletoDetails);
        return updatedCategoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Elimina una categoría de boleto por su ID.
     * 
     * @param id ID de la categoría de boleto a eliminar.
     * @return true si la categoría fue eliminada, false si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = categoriaBoletoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Obtiene todos los boletos de una categoría específica.
     * 
     * @param categoriaId ID de la categoría de boleto.
     * @return Lista de boletos en la categoría especificada.
     */
    @GetMapping("/{categoriaId}/boletos")
    public ResponseEntity<List<Boleto>> getBoletosByCategoria(@PathVariable Integer categoriaId) {
        List<Boleto> boletos = categoriaBoletoService.getBoletosByCategoria(categoriaId);
        return ResponseEntity.ok(boletos);
    }

    /**
     * Agrega un boleto a una categoría de boleto.
     * 
     * @param categoriaId ID de la categoría de boleto.
     * @param boleto El boleto a agregar.
     * @return La categoría de boleto actualizada con el nuevo boleto.
     */
    @PostMapping("/{categoriaId}/boletos")
    public ResponseEntity<CategoriaBoleto> addBoletoToCategoria(@PathVariable Integer categoriaId, @RequestBody Boleto boleto) {
        Optional<CategoriaBoleto> updatedCategoria = categoriaBoletoService.addBoletoToCategoria(categoriaId, boleto);
        return updatedCategoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
