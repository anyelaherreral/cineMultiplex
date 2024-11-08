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
import co.edu.ufps.services.PeliculaService;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
	
	@Autowired
	private PeliculaService peliculaService;

	@GetMapping
	public List<Pelicula>  list() {
		
		return peliculaService.list();
	}

	@PostMapping
	public Pelicula create(@RequestBody Pelicula Pelicula) {
		return peliculaService.create(Pelicula);
	}

//	@GetMapping("/{nombre}")
//	public List<Pelicula> getByNombre(@PathVariable String nombre) {
//		List<Pelicula> nombres = peliculaService.getByNombre(nombre);
//		return nombres;
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Pelicula> update(@PathVariable Integer id, @RequestBody Pelicula PeliculaDetails) {
		Optional<Pelicula> updatedPelicula = peliculaService.update(id, PeliculaDetails);
		return updatedPelicula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/titulo/{titulo}")
	public ResponseEntity<Pelicula> update(@PathVariable String titulo, @RequestBody Pelicula PeliculaDetails) {
		Optional<Pelicula> updatedPelicula = peliculaService.update(titulo, PeliculaDetails);
		return updatedPelicula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{titulo}")
    public ResponseEntity<Pelicula> getByNombre(@PathVariable String titulo) {
        Optional<Pelicula> pelicula = peliculaService.getByNombre(titulo);
        return pelicula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
//	@DeleteMapping("/{titulo}")
//	public ResponseEntity<Void> delete(@PathVariable String titulo) {
//		boolean deleted = peliculaService.delete(titulo);
//		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = peliculaService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
