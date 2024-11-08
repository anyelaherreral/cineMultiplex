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

import co.edu.ufps.entities.Estado;
import co.edu.ufps.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController{
	
	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public List<Estado>  list() {
		
		return estadoService.list();
	}

	@PostMapping
	public Estado create(@RequestBody Estado Estado) {
		return estadoService.create(Estado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> getById(@PathVariable Integer id) {
		Optional<Estado> Estado = estadoService.getById(id);
		return Estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@PathVariable Integer id, @RequestBody Estado EstadoDetails) {
		Optional<Estado> updatedEstado = estadoService.update(id, EstadoDetails);
		return updatedEstado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = estadoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
//	@GetMapping("/{descripcion}")
//    public ResponseEntity<Estado> getByDescripcion(@PathVariable String descripcion) {
//        Optional<Estado> estado = estadoService.getByDescripcion(descripcion);
//        return estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
	
	@PostMapping("/{id}/add_asientos/{asientoId}")
	public Estado create(@PathVariable Integer id, @PathVariable Integer asientoId) {
		Estado nuevaEstado = estadoService.addAsiento(id, asientoId);
		return nuevaEstado;
	}

}
