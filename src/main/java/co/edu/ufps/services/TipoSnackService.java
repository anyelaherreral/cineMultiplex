package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Snack;
import co.edu.ufps.entities.TipoSnack; 
import co.edu.ufps.repositories.SnackRepository;
import co.edu.ufps.repositories.TipoSnackRepository;


@Service
public class TipoSnackService {

	@Autowired
	TipoSnackRepository tipoSnackRepository;
	@Autowired
	public SnackRepository snackRepository;

	
	public List<TipoSnack> list() {
		return tipoSnackRepository.findAll();
	}
	
	public TipoSnack create(TipoSnack tipoSnack) {
		return tipoSnackRepository.save(tipoSnack);
	}
 
	public Optional<TipoSnack> getById(Integer id) {
		return tipoSnackRepository.findById(id);
	}
 
	public Optional<TipoSnack> update(Integer id, TipoSnack tipoSnackDetails) {
		Optional<TipoSnack> optionaltipoSnack = tipoSnackRepository.findById(id);
		if (!optionaltipoSnack.isPresent()) {
			return Optional.empty();
		}

		TipoSnack tipoSnack = optionaltipoSnack.get();
 
		tipoSnack.setDescripcion(tipoSnackDetails.getDescripcion());

		return Optional.of(tipoSnackRepository.save(tipoSnack));
	}
 
	public boolean delete(Integer id) {
		if (!tipoSnackRepository.existsById(id)) {
			return false;
		}

		tipoSnackRepository.deleteById(id);
		return true;
	}
	
	public TipoSnack addSnack(Integer id, String snackId) {

		Optional<TipoSnack> tipoSnackOpt = tipoSnackRepository.findById(id);

		if (tipoSnackOpt.isPresent()) {
			TipoSnack tipoSnack = tipoSnackOpt.get();
			Optional<Snack> snackOpt = snackRepository.findById(snackId);
			if (snackOpt.isPresent()) {
				tipoSnack.addSnack(snackOpt.get());
			}
			return tipoSnackRepository.save(tipoSnack);
		}
		return null;
	}
	
	public List<TipoSnack> findByDescripcion(String descripcion) {
        return tipoSnackRepository.findByDescripcion(descripcion);
    }
}
