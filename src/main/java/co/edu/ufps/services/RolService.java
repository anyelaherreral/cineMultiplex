package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Empleado;
import co.edu.ufps.entities.Rol;
import co.edu.ufps.repositories.EmpleadoRepository;
import co.edu.ufps.repositories.RolRepository;


@Service
public class RolService {

	@Autowired
	RolRepository rolRepository;
	@Autowired
	public EmpleadoRepository empleadoRepository;

	
	public List<Rol> list() {
		return rolRepository.findAll();
	}
	
	public Rol create(Rol rol) {
		return rolRepository.save(rol);
	}

	// Obtener un rol por ID
	public Optional<Rol> getById(Integer id) {
		return rolRepository.findById(id);
	}

	// Actualizar un rol existente
	public Optional<Rol> update(Integer id, Rol rolDetails) {
		Optional<Rol> optionalrol = rolRepository.findById(id);
		if (!optionalrol.isPresent()) {
			return Optional.empty();
		}

		Rol rol = optionalrol.get();

		// Actualiza otros campos según sea necesario
		rol.setDescripcion(rol.getDescripcion());

		return Optional.of(rolRepository.save(rol));
	}

	// Eliminar un rol por ID
	public boolean delete(Integer id) {
		if (!rolRepository.existsById(id)) {
			return false;
		}

		rolRepository.deleteById(id);
		return true;
	}
	
	public Rol addEmpleado(Integer id, Integer empleadoId) {

		Optional<Rol> rolOpt = rolRepository.findById(id);

		if (rolOpt.isPresent()) {
			Rol rol = rolOpt.get();
			Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);
			if (empleadoOpt.isPresent()) {
				rol.addEmpleado(empleadoOpt.get());
			}
			return rolRepository.save(rol);
		}
		return null;
	}
}
