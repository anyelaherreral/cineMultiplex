package co.edu.ufps.services;

import java.sql.Time;
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
	
	 // Listar todas las promociones
	public List<Promocion> list() {
		return promocionRepository.findAll();
	}
	
	 // Crear una nueva función
	public Promocion create(Promocion promocion) {
		return promocionRepository.save(promocion);
	}

	// Obtener un promocion por ID
	public Optional<Promocion> getById(Integer id) {
		return promocionRepository.findById(id);
	}

	// Actualizar un promocion existente
	public Optional<Promocion> update(Integer id, Promocion promocionDetails) {
		Optional<Promocion> optionalpromocion = promocionRepository.findById(id);
		if (!optionalpromocion.isPresent()) {
			return Optional.empty();
		}

		Promocion promocion = optionalpromocion.get();

		// Actualiza otros campos según sea necesario
		promocion.setDescripcion(promocion.getDescripcion());
		promocion.setFechaInicio(promocion.getFechaInicio());
		promocion.setFechaFin(promocion.getFechaFin());

		return Optional.of(promocionRepository.save(promocion));
	}

	// Eliminar un promocion por ID
	public boolean delete(Integer id) {
		if (!promocionRepository.existsById(id)) {
			return false;
		}

		promocionRepository.deleteById(id);
		return true;
	}
	
	// Obtener promociones por fecha específica
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
