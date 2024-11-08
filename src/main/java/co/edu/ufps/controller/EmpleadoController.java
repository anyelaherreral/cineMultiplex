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

import co.edu.ufps.entities.Empleado;
import co.edu.ufps.entities.Rol;
import co.edu.ufps.services.EmpleadoService;
import co.edu.ufps.services.RolService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private RolService rolService;

	@GetMapping
	public List<Empleado> list() {
		return empleadoService.list();
	}

//	@PostMapping
//	public Empleado create(@RequestBody Empleado Empleado) {
//		return empleadoService.create(Empleado);
//	}

	@PostMapping
	public ResponseEntity<Empleado> create(@RequestBody Empleado empleadoRequest) {
		// Buscar el rol por ID
		Rol rol = rolService.getById(empleadoRequest.getRol().getId())
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

		// Asignar el rol al empleado
		empleadoRequest.setRol(rol);

		// Crear el empleado
		Empleado nuevoEmpleado = empleadoService.create(empleadoRequest);

		return ResponseEntity.ok(nuevoEmpleado); // Devolvemos el empleado creado
	}

	@GetMapping("/{documento}")
	public List<Empleado> getByDocumento(@PathVariable String documento) {
		List<Empleado> empleados = empleadoService.getByDocumento(documento);
		return empleados;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody Empleado EmpleadoDetails) {
		Optional<Empleado> updatedEmpleado = empleadoService.update(id, EmpleadoDetails);
		return updatedEmpleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = empleadoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	@PostMapping("/{id}/add_funcions/{funcionId}")
	public Empleado create(@PathVariable Integer id, @PathVariable Integer funcionId) {
		Empleado nuevaTipoPersona = empleadoService.addFuncion(id, funcionId);
		return nuevaTipoPersona;
	}

}
