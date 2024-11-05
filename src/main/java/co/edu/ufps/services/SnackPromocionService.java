package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.SnackPromocion;
import co.edu.ufps.repositories.SnackPromocionRepository;


@Service
public class SnackPromocionService {

	@Autowired
	SnackPromocionRepository snackPromocionRepository;
	
	public List<SnackPromocion> list() {
		return snackPromocionRepository.findAll();
	}
	
	public SnackPromocion create(SnackPromocion snackPromocion) {
		return snackPromocionRepository.save(snackPromocion);
	}

	// Obtener un snackPromocion por ID
	public Optional<SnackPromocion> getByDocumento(Integer documento) {
		return snackPromocionRepository.findById(documento);
	}

	// Actualizar un snackPromocion existente
	public Optional<SnackPromocion> update(Integer id, SnackPromocion snackPromocionDetails) {
		Optional<SnackPromocion> optionalsnackPromocion = snackPromocionRepository.findById(id);
		if (!optionalsnackPromocion.isPresent()) {
			return Optional.empty();
		}

		SnackPromocion snackPromocion = optionalsnackPromocion.get();

		// Actualiza otros campos según sea necesario
		snackPromocion.setDescuento(snackPromocion.getDescuento());
		
		return Optional.of(snackPromocionRepository.save(snackPromocion));
	}

	// Eliminar un snackPromocion por ID
	public boolean delete(Integer id) {
		if (!snackPromocionRepository.existsById(id)) {
			return false;
		}

		snackPromocionRepository.deleteById(id);
		return true;
	}
}
