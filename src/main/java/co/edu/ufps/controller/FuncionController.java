package co.edu.ufps.controller;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Funcion;
import co.edu.ufps.services.FuncionService;

@RestController
@RequestMapping("/funciones")
public class FuncionController {
	
	@Autowired
	private FuncionService funcionService;

	@GetMapping
	public List<Funcion>  list() {
		
		return funcionService.list();
	}

	@PostMapping
	public Funcion create(@RequestBody Funcion Funcion) {
		return funcionService.create(Funcion);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcion> getById(@PathVariable Integer id) {
		Optional<Funcion> Funcion = funcionService.getById(id);
		return Funcion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/fecha/{fecha}")
	public ResponseEntity<List<Funcion>>getByGrupo(@PathVariable LocalDate fecha) {
		List<Funcion> selecciones = funcionService.listFuncionesPorFecha(fecha);
		if (selecciones.isEmpty()) {
			return ResponseEntity.notFound().build(); // Retorna un 404 si no hay resultados para ese rol
		}
		return ResponseEntity.ok(selecciones); // Retorna un 200 con la lista de resultados
	}
	
	@PutMapping("/{funcionId}/pelicula/{peliculaId}")
    public Funcion agregarPeliculaAFuncion(@PathVariable Integer funcionId, @PathVariable Integer peliculaId) {
        return funcionService.agregarPeliculaAFuncion(funcionId, peliculaId);
    }


	@PutMapping("/{id}")
	public ResponseEntity<Funcion> update(@PathVariable Integer id, @RequestBody Funcion FuncionDetails) {
		Optional<Funcion> updatedFuncion = funcionService.update(id, FuncionDetails);
		return updatedFuncion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = funcionService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
