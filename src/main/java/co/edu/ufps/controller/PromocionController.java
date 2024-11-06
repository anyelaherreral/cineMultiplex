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

import co.edu.ufps.entities.Promocion;
import co.edu.ufps.entities.Rol;
import co.edu.ufps.services.PromocionService;

@RestController
@RequestMapping("/promociones")
public class PromocionController {
	
	@Autowired
	private PromocionService promocionService;

	@GetMapping
	public List<Promocion>  list() {
		
		return promocionService.list();
	}

	@PostMapping
	public Promocion create(@RequestBody Promocion Promocion) {
		return promocionService.create(Promocion);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Promocion> getById(@PathVariable Integer id) {
		Optional<Promocion> Promocion = promocionService.getById(id);
		return Promocion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Promocion> update(@PathVariable Integer id, @RequestBody Promocion PromocionDetails) {
		Optional<Promocion> updatedPromocion = promocionService.update(id, PromocionDetails);
		return updatedPromocion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = promocionService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	 // Obtener promociones por fecha
    @GetMapping("/{fecha_inicio}")
    public List<Promocion> getByFecha(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return promocionService.findByFechaInicio(fecha);
    }
    

    // Obtener promociones por horario
    @GetMapping("/{fecha_fin}")
    public List<Promocion> getByHorario(@PathVariable Time horario) {
        return promocionService.findByFechaFin(horario);
    }
    
    @GetMapping("/{descripcion}")
    public List<Promocion> getByDescripcion(@PathVariable String descripcion) {
        return promocionService.findByDescripcion(descripcion);
    }

}
