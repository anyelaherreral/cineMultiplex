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

import co.edu.ufps.entities.TipoSnack;
import co.edu.ufps.services.TipoSnackService;

@RestController
@RequestMapping("/tipoSnacks")
public class TipoSnackController{
	
	@Autowired
	private TipoSnackService tipoSnackService;

	@GetMapping
	public List<TipoSnack>  list() {
		
		return tipoSnackService.list();
	}

	@PostMapping
	public TipoSnack create(@RequestBody TipoSnack TipoSnack) {
		return tipoSnackService.create(TipoSnack);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoSnack> getById(@PathVariable Integer id) {
		Optional<TipoSnack> TipoSnack = tipoSnackService.getById(id);
		return TipoSnack.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<TipoSnack> update(@PathVariable Integer id, @RequestBody TipoSnack TipoSnackDetails) {
		Optional<TipoSnack> updatedTipoSnack = tipoSnackService.update(id, TipoSnackDetails);
		return updatedTipoSnack.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = tipoSnackService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/{id}/add_snacks/{snackId}")
	public TipoSnack create(@PathVariable Integer id, @PathVariable String snackId) {
		TipoSnack nuevaTipoSnack = tipoSnackService.addSnack(id, snackId);
		return nuevaTipoSnack;
	}
	
	@GetMapping("/buscarSnack")
	public List<TipoSnack> findByDescripcion(@RequestParam String descripcion) {
        return tipoSnackService.findByDescripcion(descripcion);
    }

}
