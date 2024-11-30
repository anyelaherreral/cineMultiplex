package co.edu.ufps.services;
 
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Promocion;
import co.edu.ufps.repositories.PromocionRepository;


@Service
public class PromocionService {

	@Autowired
	PromocionRepository promocionRepository;
	 
	public List<Promocion> list() {
		return promocionRepository.findAll();
	}
	 
	public Promocion create(Promocion promocion) {
		return promocionRepository.save(promocion);
	}
 
	public Optional<Promocion> getById(Integer id) {
		return promocionRepository.findById(id);
	}
 
	public Optional<Promocion> update(Integer id, Promocion promocionDetails) {
		Optional<Promocion> optionalpromocion = promocionRepository.findById(id);
		if (!optionalpromocion.isPresent()) {
			return Optional.empty();
		}

		Promocion promocion = optionalpromocion.get();
 
		promocion.setDescripcion(promocionDetails.getDescripcion());
		promocion.setFechaInicio(promocionDetails.getFechaInicio());
		promocion.setFechaFin(promocionDetails.getFechaFin());

		return Optional.of(promocionRepository.save(promocion));
	}
 
	public boolean delete(Integer id) {
		if (!promocionRepository.existsById(id)) {
			return false;
		}

		promocionRepository.deleteById(id);
		return true;
	}
	 
    public List<Promocion> findByFechaInicio(Date fecha) {
        return promocionRepository.findByFechaInicio(fecha);
    }
    
    public List<Promocion> findByFechaFin(Date fecha) {
        return promocionRepository.findByFechaFin(fecha);
    }
    
    public List<Promocion> findByDescripcion(String descripcion) {
        return promocionRepository.findByDescripcion(descripcion);
    }
      
    
}
