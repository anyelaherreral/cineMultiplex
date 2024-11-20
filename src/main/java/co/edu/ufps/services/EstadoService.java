	package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Estado;
import co.edu.ufps.entities.MetodoPago;
import co.edu.ufps.repositories.AsientoRepository;
import co.edu.ufps.repositories.EstadoRepository;


@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	public AsientoRepository asientoRepository;

	
	public List<Estado> list() {
		return estadoRepository.findAll();
	}
	
	public Estado create(Estado estado) {
		return estadoRepository.save(estado);
	}

	// Obtener un estado por ID
	public Optional<Estado> getById(Integer id) {
		return estadoRepository.findById(id);
	}

	// Actualizar un estado existente
	public Optional<Estado> update(Integer id, Estado estadoDetails) {
		Optional<Estado> optionalestado = estadoRepository.findById(id);
		if (!optionalestado.isPresent()) {
			return Optional.empty();
		}

		Estado estado = optionalestado.get();

		// Actualiza otros campos según sea necesario
		estado.setDescripcion(estadoDetails.getDescripcion());

		return Optional.of(estadoRepository.save(estado));
	}

	// Eliminar un estado por ID
	public boolean delete(Integer id) {
		if (!estadoRepository.existsById(id)) {
			return false;
		}

		estadoRepository.deleteById(id);
		return true;
	}
	
	public Estado addAsiento(Integer id, Integer asientoId) {

		Optional<Estado> estadoOpt = estadoRepository.findById(id);

		if (estadoOpt.isPresent()) {
			Estado estado = estadoOpt.get();
			Optional<Asiento> asientoOpt = asientoRepository.findById(asientoId);
			if (asientoOpt.isPresent()) {
				estado.addAsiento(asientoOpt.get());
			}
			return estadoRepository.save(estado);
		}
		return null;
	}
}
