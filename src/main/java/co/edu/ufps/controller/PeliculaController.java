package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
 
	@DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable Integer id) {
        if (!peliculaService.delete(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la película.");
        }
        return ResponseEntity.ok("Película eliminada exitosamente.");
    }
	
	@GetMapping("/{id}/tiene-funciones")
    public ResponseEntity<Boolean> tieneFunciones(@PathVariable Integer id) {
        boolean tieneFunciones = peliculaService.tieneFuncionesAsociadas(id);
        return ResponseEntity.ok(tieneFunciones);
    }

}
