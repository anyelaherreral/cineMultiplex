package co.edu.ufps.services;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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

	// Listar todas las funciones
	public List<Funcion> list() {
		return funcionRepository.findAll();
	}

	// Crear una nueva función
	public Funcion create(Funcion funcion) {
		return funcionRepository.save(funcion);
	}

	// Obtener un funcion por ID
	public Optional<Funcion> getById(Integer id) {
		return funcionRepository.findById(id);
	}

	// Método para agregar una película a una función
	public Funcion agregarPeliculaAFuncion(Integer funcionId, Integer peliculaId) {
		// Buscar la función por su ID
		Funcion funcion = funcionRepository.findById(funcionId)
				.orElseThrow(() -> new RuntimeException("Función no encontrada"));

		// Buscar la película por su ID
		Pelicula pelicula = peliculaRepository.findById(peliculaId)
				.orElseThrow(() -> new RuntimeException("Película no encontrada"));

		// Asignar la película a la función
		funcion.setPelicula(pelicula);

		// Guardar la función con la película asignada
		return funcionRepository.save(funcion);
	}

	// Actualizar un funcion existente
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


	public boolean delete(Integer id) {
		if (!funcionRepository.existsById(id)) {
			return false;
		}

		funcionRepository.deleteById(id);
		return true;
	}

	// Obtener funciones por fecha
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
        // Buscar la función por su ID
        Funcion funcion = funcionRepository.findById(funcionId)
                .orElseThrow(() -> new IllegalArgumentException("La función con ID " + funcionId + " no existe."));

        // Obtener la sala asociada a la función
        Sala sala = funcion.getSala(); // Suponiendo que Funcion tiene una relación con Sala

        // Verificar que la sala existe
        if (sala == null) {
            throw new IllegalArgumentException("La sala asociada a la función no existe.");
        }

        // Obtener los asientos de la sala
        List<Asiento> asientosSala = sala.getAsientos(); // Suponiendo que Sala tiene una lista de Asientos

        // Filtrar solo los asientos que están disponibles (estado = 'Disponible')
        List<Asiento> asientosDisponibles = asientosSala.stream()
                .filter(asiento -> "Disponible".equalsIgnoreCase(asiento.getEstado().getDescripcion()))
                .collect(Collectors.toList());

        // Devolver los asientos disponibles de la sala asociada a la función
        return asientosDisponibles;
    }

}
