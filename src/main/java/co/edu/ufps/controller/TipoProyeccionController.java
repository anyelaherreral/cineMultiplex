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
import co.edu.ufps.entities.TipoProyeccion;
import co.edu.ufps.services.TipoProyeccionService;

@RestController
@RequestMapping("/tipoProyeccions")
public class TipoProyeccionController {

	@Autowired
	private TipoProyeccionService tipoProyeccionService;

	@GetMapping
	public List<TipoProyeccion> list() {
		return tipoProyeccionService.list();
	}

	@PostMapping
	public TipoProyeccion create(@RequestBody TipoProyeccion TipoProyeccion) {
		return tipoProyeccionService.create(TipoProyeccion);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoProyeccion> getById(@PathVariable Integer id) {
		Optional<TipoProyeccion> TipoProyeccion = tipoProyeccionService.getById(id);
		return TipoProyeccion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoProyeccion> update(@PathVariable Integer id, @RequestBody TipoProyeccion TipoProyeccionDetails) {
		Optional<TipoProyeccion> updatedTipoProyeccion = tipoProyeccionService.update(id, TipoProyeccionDetails);
		return updatedTipoProyeccion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = tipoProyeccionService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
