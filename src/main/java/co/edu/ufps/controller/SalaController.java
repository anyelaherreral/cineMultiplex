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

import co.edu.ufps.entities.Pelicula;
import co.edu.ufps.entities.Sala;
import co.edu.ufps.services.SalaService;

@RestController
@RequestMapping("/salas")
public class SalaController {
	
	@Autowired
	private SalaService salaService;

	@GetMapping
	public List<Sala>  list() {
		
		return salaService.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sala> getById(@PathVariable Integer id) {
		Optional<Sala> Sala = salaService.getById(id);
		return Sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Sala create(@RequestBody Sala Sala) {
		return salaService.create(Sala);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Sala> update(@PathVariable Integer id, @RequestBody Sala SalaDetails) {
		Optional<Sala> updatedSala = salaService.update(id, SalaDetails);
		return updatedSala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = salaService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	

}
