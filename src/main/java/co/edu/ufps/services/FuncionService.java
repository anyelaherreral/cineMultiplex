package co.edu.ufps.services;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Pelicula;
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
        Funcion funcion = funcionRepository.findById(funcionId).orElseThrow(() -> new RuntimeException("Función no encontrada"));

        // Buscar la película por su ID
        Pelicula pelicula = peliculaRepository.findById(peliculaId).orElseThrow(() -> new RuntimeException("Película no encontrada"));

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

		// Actualiza otros campos según sea necesario
		//funcion.setHorario(funcion.getHorario());
		funcion.setHorario(funcion.getHorario());
		funcion.setSala(funcion.getSala());

		return Optional.of(funcionRepository.save(funcion));
	}

	// Eliminar un funcion por ID
	public boolean delete(Integer id) {
		if (!funcionRepository.existsById(id)) {
			return false;
		}

		funcionRepository.deleteById(id);
		return true;
	}
	
	
	//Obtener funciones por fecha
		public List<Funcion> listFuncionesPorFecha(LocalDate fecha) {
			return funcionRepository.findByFecha(fecha);
		}

      
    
}
