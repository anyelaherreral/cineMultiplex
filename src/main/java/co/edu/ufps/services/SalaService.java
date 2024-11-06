package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Sala;
import co.edu.ufps.entities.Sala;
import co.edu.ufps.repositories.AsientoRepository;
import co.edu.ufps.repositories.FuncionRepository;
import co.edu.ufps.repositories.SalaRepository;


@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	FuncionRepository funcionRepository;
	
	@Autowired
	AsientoRepository asientoRepository;
	
	public List<Sala> list() {
		return salaRepository.findAll();
	}
	
	public Sala create(Sala sala) {
		return salaRepository.save(sala);
	}

	// Obtener un sala por ID
	public Optional<Sala> getByDocumento(Integer documento) {
		return salaRepository.findById(documento);
	}

	// Actualizar un sala existente
	public Optional<Sala> update(Integer id, Sala salaDetails) {
		Optional<Sala> optionalsala = salaRepository.findById(id);
		if (!optionalsala.isPresent()) {
			return Optional.empty();
		}

		Sala sala = optionalsala.get();

		// Actualiza otros campos según sea necesario
		sala.setNum_asientos(sala.getNum_asientos());

		return Optional.of(salaRepository.save(sala));
	}

	// Eliminar un sala por ID
	public boolean delete(Integer id) {
		if (!salaRepository.existsById(id)) {
			return false;
		}

		salaRepository.deleteById(id);
		return true;
	}
	
	public Sala addFuncion(Integer id, Integer funcionId) {

		Optional<Sala> salaOpt = salaRepository.findById(id);

		if (salaOpt.isPresent()) {
			Sala sala = salaOpt.get();
			Optional<Funcion> funcionOpt = funcionRepository.findById(funcionId);
			if (funcionOpt.isPresent()) {
				sala.addFuncion(funcionOpt.get());
			}
			return salaRepository.save(sala);
		}
		return null;
	}
	
	public Sala addAsiento(Integer id, Integer asientoId) {

		Optional<Sala> salaOpt = salaRepository.findById(id);

		if (salaOpt.isPresent()) {
			Sala sala = salaOpt.get();
			Optional<Asiento> asientoOpt = asientoRepository.findById(asientoId);
			if (asientoOpt.isPresent()) {
				sala.addAsiento(asientoOpt.get());
			}
			return salaRepository.save(sala);
		}
		return null;
	}
	
}
