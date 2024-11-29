package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Empleado;
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Rol;
import co.edu.ufps.repositories.FuncionRepository;
import co.edu.ufps.repositories.RolRepository;
import co.edu.ufps.repositories.EmpleadoRepository;


@Service
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	FuncionRepository funcionRepository;
	
	@Autowired
    private RolRepository rolRepository;
	
	public List<Empleado> list() {
		return empleadoRepository.findAll();
	}
	
	public Empleado create(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}
	

	// Obtener un empleado por ID
	public Optional<Empleado> getByDocumento(String documento) {
		return empleadoRepository.findByDocumento(documento);
	}

	// Actualizar un empleado existente
	public Optional<Empleado> update(Integer id, Empleado empleadoDetails) {
		Optional<Empleado> optionalempleado = empleadoRepository.findById(id);
		if (!optionalempleado.isPresent()) {
			return Optional.empty();
		}

		Empleado empleado = optionalempleado.get();

		// Actualiza otros campos según sea necesario
		empleado.setNombre(empleadoDetails.getNombre());
		empleado.setDocumento(empleadoDetails.getDocumento());
		empleado.setEmail(empleadoDetails.getEmail());
		empleado.setRol(empleadoDetails.getRol());
		empleado.setContrasena(empleadoDetails.getContrasena());

		return Optional.of(empleadoRepository.save(empleado));
	}

	// Eliminar un empleado por ID
	public boolean delete(Integer id) {
		if (funcionRepository.existsEmpleadoInFunciones(id)) {
            return false;
        }
        if (!empleadoRepository.existsById(id)) {
            return false; 
        }
        empleadoRepository.deleteById(id);
        return true;
	}
	
	public Empleado addFuncion(Integer id, Integer funcionId) {

		Optional<Empleado> tipoPersonaOpt = empleadoRepository.findById(id);

		if (tipoPersonaOpt.isPresent()) {

			Empleado tipoPersona = tipoPersonaOpt.get();

			Optional<Funcion> funcionOpt = funcionRepository.findById(funcionId);

			if (funcionOpt.isPresent()) {
				tipoPersona.addFuncion(funcionOpt.get());
			}
			return empleadoRepository.save(tipoPersona);
		}

		return null;
	}
	
	public Empleado actualizarRol(Integer empleadoId, Integer rolId) {
        Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow(() -> 
            new RuntimeException("Empleado no encontrado con ID: " + empleadoId)
        );

        Rol rol = rolRepository.findById(rolId).orElseThrow(() -> 
            new RuntimeException("Rol no encontrado con ID: " + rolId)
        );

        empleado.setRol(rol);
        return empleadoRepository.save(empleado);
    }
	
	public List<Funcion> obtenerFuncionesDeEmpleado(Integer empleadoId) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);
        
        // Si el empleado no existe, devolvemos null
        if (!empleadoOpt.isPresent()) {
            return null;
        }
        
        Empleado empleado = empleadoOpt.get();
        return empleado.getFunciones(); // Suponiendo que tienes una relación bidireccional en la entidad Empleado
    }
	
	public void eliminarFuncionDeEmpleado(Integer empleadoId, Integer funcionId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Funcion funcion = funcionRepository.findById(funcionId)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        empleado.getFunciones().remove(funcion); // Aquí se elimina la función de la lista de funciones del empleado
        empleadoRepository.save(empleado);  // Persistir los cambios
    }
	
	
}
