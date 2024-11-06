package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Empleado;
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.repositories.FuncionRepository;
import co.edu.ufps.repositories.EmpleadoRepository;


@Service
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	FuncionRepository funcionRepository;
	
	public List<Empleado> list() {
		return empleadoRepository.findAll();
	}
	
	public Empleado create(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	// Obtener un empleado por ID
	public List<Empleado> getByDocumento(String documento) {
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
		empleado.setNombre(empleado.getNombre());
		empleado.setDocumento(empleado.getDocumento());
		empleado.setEmail(empleado.getEmail());
		empleado.setRol(empleado.getRol());

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
}
