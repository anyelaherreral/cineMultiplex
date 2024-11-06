package co.edu.ufps.controller;

import java.sql.Time;
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
	
	 // Obtener funciones por fecha
    @GetMapping("/fecha")
    public List<Funcion> getByFecha(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return funcionService.findByFecha(fecha);
    }
    

    // Obtener funciones por horario
    @GetMapping("/horario")
    public List<Funcion> getByHorario(@PathVariable Time horario) {
        return funcionService.findByHorario(horario);
    }

}
