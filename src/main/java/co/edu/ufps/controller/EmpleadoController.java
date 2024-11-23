package co.edu.ufps.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Empleado;
import co.edu.ufps.entities.Funcion;
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
	
	@GetMapping("/{documento}")
	public Optional<Empleado> getByDocumento(@PathVariable String documento) {
		return  empleadoService.getByDocumento(documento);
	}


	@PostMapping
	public ResponseEntity<Empleado> create(@RequestBody Empleado empleadoRequest) {
		Rol rol = rolService.getById(empleadoRequest.getRol().getId())
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

		empleadoRequest.setRol(rol);

		Empleado nuevoEmpleado = empleadoService.create(empleadoRequest);

		return ResponseEntity.ok(nuevoEmpleado); // Devolvemos el empleado creado
	}
	

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Empleado loginRequest) {
	    Optional<Empleado> empleadoOpt = empleadoService.getByDocumento(loginRequest.getDocumento());

	    if (empleadoOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Collections.singletonMap("message", "Empleado no encontrado"));
	    }

	    Empleado empleado = empleadoOpt.get();

	    if (!empleado.getContrasena().equals(loginRequest.getContrasena())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Collections.singletonMap("message", "Contraseña incorrecta"));
	    }

	    Map<String, String> response = new HashMap<>();
	    response.put("nombre", empleado.getNombre());
	    response.put("rol", empleado.getRol().getDescripcion());

	    return ResponseEntity.ok(response); 
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
	
	@PutMapping("/actualizarRol/{empleadoId}")
    public Empleado actualizarRol(@PathVariable Integer empleadoId, @RequestBody Integer rolId) {
        return empleadoService.actualizarRol(empleadoId, rolId);
    }
	
	@GetMapping("/{empleadoId}/funciones")
    public ResponseEntity<List<Funcion>> obtenerFuncionesDeEmpleado(@PathVariable("empleadoId") Integer empleadoId) {
        List<Funcion> funciones = empleadoService.obtenerFuncionesDeEmpleado(empleadoId);
        
        if (funciones.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si no hay funciones
        }
        return ResponseEntity.ok(funciones);
    }
	
	
	@DeleteMapping("/{empleadoId}/funciones/{funcionId}")
	public ResponseEntity<Void> eliminarFuncionDeEmpleado(@PathVariable Integer empleadoId, @PathVariable Integer funcionId) {
	    empleadoService.eliminarFuncionDeEmpleado(empleadoId, funcionId);
	    return ResponseEntity.noContent().build();
	}


}
