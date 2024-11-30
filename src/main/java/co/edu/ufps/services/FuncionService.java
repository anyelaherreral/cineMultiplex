package co.edu.ufps.services;
 
import java.time.LocalDate;
 import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Pelicula;
import co.edu.ufps.entities.Sala;
import co.edu.ufps.repositories.FuncionRepository;
import co.edu.ufps.repositories.PeliculaRepository;

@Service
public class FuncionService {

	@Autowired
	FuncionRepository funcionRepository;

	@Autowired
	PeliculaRepository peliculaRepository;

	public List<Funcion> list() {
		return funcionRepository.findAll();
	}

	public Funcion create(Funcion funcion) {
		return funcionRepository.save(funcion);
	}

	public Optional<Funcion> getById(Integer id) {
		return funcionRepository.findById(id);
	}
	
	public Optional<Funcion> update(Integer id, Funcion funcionDetails) {
		Optional<Funcion> optionalfuncion = funcionRepository.findById(id);
		if (!optionalfuncion.isPresent()) {
			return Optional.empty();
		}
		Funcion funcion = optionalfuncion.get();
		funcion.setFecha(funcionDetails.getFecha());
		funcion.setHorario(funcionDetails.getHorario());
		funcion.setPelicula(funcionDetails.getPelicula());
		funcion.setSala(funcionDetails.getSala());
		
		return Optional.of(funcionRepository.save(funcion));
	}

	public Funcion agregarPeliculaAFuncion(Integer funcionId, Integer peliculaId) {
 		Funcion funcion = funcionRepository.findById(funcionId)
				.orElseThrow(() -> new RuntimeException("Función no encontrada"));

 		Pelicula pelicula = peliculaRepository.findById(peliculaId)
				.orElseThrow(() -> new RuntimeException("Película no encontrada"));

 		funcion.setPelicula(pelicula);

 		return funcionRepository.save(funcion);
	}

	public boolean delete(Integer id) {
		if (!funcionRepository.existsById(id)) {
			return false;
		}

		funcionRepository.deleteById(id);
		return true;
	}

 	public List<Funcion> listFuncionesPorFecha(LocalDate fecha) {
		return funcionRepository.findByFecha(fecha);
	}

	public List<LocalDate> obtenerFechasDisponibles() {
		List<LocalDate> fechasDisponibles = funcionRepository.obtenerFechasDisponibles();
		if (fechasDisponibles.isEmpty()) {
			return null;
		}
		return fechasDisponibles;
	}
	
	public List<Asiento> obtenerAsientosDeSalaPorFuncion(Integer funcionId) {
         Funcion funcion = funcionRepository.findById(funcionId)
                .orElseThrow(() -> new IllegalArgumentException("La función con ID " + funcionId + " no existe."));

         Sala sala = funcion.getSala();  

         if (sala == null) {
            throw new IllegalArgumentException("La sala asociada a la función no existe.");
        }

         List<Asiento> asientosSala = sala.getAsientos();  

         List<Asiento> asientosDisponibles = asientosSala.stream()
                .filter(asiento -> "Disponible".equalsIgnoreCase(asiento.getEstado().getDescripcion()))
                .collect(Collectors.toList());

         return asientosDisponibles;
    }

}
