package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.PedidoSnackPromocion;
import co.edu.ufps.entities.SnackPromocion;
import co.edu.ufps.entities.Snack;


import co.edu.ufps.repositories.SnackRepository;
import co.edu.ufps.repositories.PedidoSnackPromocionRepository;
import co.edu.ufps.repositories.SnackPromocionRepository;


@Service
public class SnackService {

	@Autowired
	SnackRepository snackRepository;
	
	@Autowired
	SnackPromocionRepository snackPromocionRepository;
	
	@Autowired
	PedidoSnackPromocionRepository pedidoSnackPromocionRepository;
	
	public List<Snack> list() {
		return snackRepository.findAll();
	}
	
	public Snack create(Snack snack) {
		return snackRepository.save(snack);
	}

	// Obtener un snack por ID
	public Optional<Snack> getByDocumento(String id) {
		return snackRepository.findById(id);
	}

	// Actualizar un snack existente
	public Optional<Snack> update(String id, Snack snackDetails) {
		Optional<Snack> optionalsnack = snackRepository.findById(id);
		if (!optionalsnack.isPresent()) {
			return Optional.empty();
		}

		Snack snack = optionalsnack.get();

		// Actualiza otros campos según sea necesario
		snack.setDescripcion(snack.getDescripcion());
		snack.setPrecio(snack.getPrecio());
		snack.setCantidadDisponible(snackDetails.getCantidadDisponible());

		return Optional.of(snackRepository.save(snack));
	}

	// Eliminar un snack por ID
	public boolean delete(String id) {
		if (!snackRepository.existsById(id)) {
			return false;
		}

		snackRepository.deleteById(id);
		return true;
	}
	
	public Snack addSnackPromocion(String id, Integer snackPromocionId) {

		Optional<Snack> snackOpt = snackRepository.findById(id);

		if (snackOpt.isPresent()) {
			Snack snack = snackOpt.get();
			Optional<SnackPromocion> snackPromocionOpt = snackPromocionRepository.findById(snackPromocionId);
			if (snackPromocionOpt.isPresent()) {
				snack.addSnackPromocion(snackPromocionOpt.get());
			}
			return snackRepository.save(snack);
		}
		return null;
	}
	
	public Snack addPedidoSnackPromocion(String id, Integer pedidoSnackPromocionId) {

		Optional<Snack> snackOpt = snackRepository.findById(id);

		if (snackOpt.isPresent()) {
			Snack snack = snackOpt.get();
			Optional<PedidoSnackPromocion> pedidoSnackPromocionOpt = pedidoSnackPromocionRepository.findById(pedidoSnackPromocionId);
			if (pedidoSnackPromocionOpt.isPresent()) {
				snack.addPedidoSnackPromocion(pedidoSnackPromocionOpt.get());
			}
			return snackRepository.save(snack);
		}
		return null;
	}
	
}
