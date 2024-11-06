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

import co.edu.ufps.entities.Genero;
import co.edu.ufps.services.GeneroService;

@RestController
@RequestMapping("/generos")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;

	@GetMapping
	public List<Genero>  list() {
		
		return generoService.list();
	}

	@PostMapping
	public Genero create(@RequestBody Genero Genero) {
		return generoService.create(Genero);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Genero> getById(@PathVariable Integer id) {
		Optional<Genero> Genero = generoService.getById(id);
		return Genero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Genero> update(@PathVariable Integer id, @RequestBody Genero GeneroDetails) {
		Optional<Genero> updatedGenero = generoService.update(id, GeneroDetails);
		return updatedGenero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = generoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{descripcion}")
    public ResponseEntity<Genero> getByDescripcion(@PathVariable String descripcion) {
        Optional<Genero> genero = generoService.getByDescripcion(descripcion);
        return genero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@PostMapping("/{id}/add_peliculas/{peliculaId}")
	public Genero create(@PathVariable Integer id, @PathVariable Integer peliculaId) {
		Genero nuevaGenero = generoService.addPelicula(id, peliculaId);
		return nuevaGenero;
	}

}
