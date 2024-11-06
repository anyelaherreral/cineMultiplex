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

import co.edu.ufps.entities.Rol;
import co.edu.ufps.services.RolService;

@RestController
@RequestMapping("/rols")
public class RolController {
	
	@Autowired
	private RolService rolService;

	@GetMapping
	public List<Rol>  list() {
		
		return rolService.list();
	}

	@PostMapping
	public Rol create(@RequestBody Rol Rol) {
		return rolService.create(Rol);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rol> getById(@PathVariable Integer id) {
		Optional<Rol> Rol = rolService.getById(id);
		return Rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Rol> update(@PathVariable Integer id, @RequestBody Rol RolDetails) {
		Optional<Rol> updatedRol = rolService.update(id, RolDetails);
		return updatedRol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = rolService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{descripcion}")
    public ResponseEntity<Rol> getByDescripcion(@PathVariable String descripcion) {
        Optional<Rol> rol = rolService.getByDescripcion(descripcion);
        return rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@PostMapping("/{id}/add_empleados/{empleadoId}")
	public Rol create(@PathVariable Integer id, @PathVariable Integer empleadoId) {
		Rol nuevaRol = rolService.addEmpleado(id, empleadoId);
		return nuevaRol;
	}

}
