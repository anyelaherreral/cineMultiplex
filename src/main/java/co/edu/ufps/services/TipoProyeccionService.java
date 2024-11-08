package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Sala;
import co.edu.ufps.entities.TipoProyeccion;
import co.edu.ufps.repositories.SalaRepository;
import co.edu.ufps.repositories.TipoProyeccionRepository;


@Service
public class TipoProyeccionService {

	@Autowired
	TipoProyeccionRepository tipoProyeccionRepository;
	@Autowired
	public SalaRepository salaRepository;

	
	public List<TipoProyeccion> list() {
		return tipoProyeccionRepository.findAll();
	}
	
	public TipoProyeccion create(TipoProyeccion tipoProyeccion) {
		return tipoProyeccionRepository.save(tipoProyeccion);
	}

	// Obtener un tipoProyeccion por ID
	public Optional<TipoProyeccion> getById(Integer id) {
		return tipoProyeccionRepository.findById(id);
	}
	
//	public Optional<TipoProyeccion> getByDescripcion(String descripcion) {
//		return tipoProyeccionRepository.findByDescripcion(descripcion);
//	}


	// Actualizar un tipoProyeccion existente
	public Optional<TipoProyeccion> update(Integer id, TipoProyeccion tipoProyeccionDetails) {
		Optional<TipoProyeccion> optionaltipoProyeccion = tipoProyeccionRepository.findById(id);
		if (!optionaltipoProyeccion.isPresent()) {
			return Optional.empty();
		}

		TipoProyeccion tipoProyeccion = optionaltipoProyeccion.get();

		// Actualiza otros campos según sea necesario
		tipoProyeccion.setDescripcion(tipoProyeccionDetails.getDescripcion());

		return Optional.of(tipoProyeccionRepository.save(tipoProyeccion));
	}

	// Eliminar un tipoProyeccion por ID
	public boolean delete(Integer id) {
		if (!tipoProyeccionRepository.existsById(id)) {
			return false;
		}

		tipoProyeccionRepository.deleteById(id);
		return true;
	}
	
	public TipoProyeccion addSala(Integer id, Integer salaId) {

		Optional<TipoProyeccion> tipoProyeccionOpt = tipoProyeccionRepository.findById(id);

		if (tipoProyeccionOpt.isPresent()) {
			TipoProyeccion tipoProyeccion = tipoProyeccionOpt.get();
			Optional<Sala> salaOpt = salaRepository.findById(salaId);
			if (salaOpt.isPresent()) {
				tipoProyeccion.addSala(salaOpt.get());
			}
			return tipoProyeccionRepository.save(tipoProyeccion);
		}
		return null;
	}
}
