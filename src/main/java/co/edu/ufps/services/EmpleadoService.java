package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Empleado;
import co.edu.ufps.repositories.EmpleadoRepository;


@Service
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	public List<Empleado> list() {
		return empleadoRepository.findAll();
	}
	
	public Empleado create(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	// Obtener un empleado por ID
	public Optional<Empleado> getByDocumento(Integer documento) {
		return empleadoRepository.findById(documento);
	}

	// Actualizar un empleado existente
	public Optional<Empleado> update(Integer id, Empleado empleadoDetails) {
		Optional<Empleado> optionalempleado = empleadoRepository.findById(id);
		if (!optionalempleado.isPresent()) {
			return Optional.empty();
		}

		Empleado empleado = optionalempleado.get();

		// Actualiza otros campos según sea necesario
		empleado.setNombre(empleado.getNombre());
		empleado.setEmail(empleado.getEmail());

		return Optional.of(empleadoRepository.save(empleado));
	}

	// Eliminar un empleado por ID
	public boolean delete(Integer id) {
		if (!empleadoRepository.existsById(id)) {
			return false;
		}

		empleadoRepository.deleteById(id);
		return true;
	}
}
