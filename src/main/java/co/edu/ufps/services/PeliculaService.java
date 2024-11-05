package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Pelicula;
import co.edu.ufps.repositories.PeliculaRepository;


@Service
public class PeliculaService {

	@Autowired
	PeliculaRepository peliculaRepository;
	
	public List<Pelicula> list() {
		return peliculaRepository.findAll();
	}
	
	public Pelicula create(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}

	// Obtener un pelicula por ID
	public Optional<Pelicula> getByDocumento(Integer documento) {
		return peliculaRepository.findById(documento);
	}

	// Actualizar un pelicula existente
	public Optional<Pelicula> update(Integer id, Pelicula peliculaDetails) {
		Optional<Pelicula> optionalpelicula = peliculaRepository.findById(id);
		if (!optionalpelicula.isPresent()) {
			return Optional.empty();
		}

		Pelicula pelicula = optionalpelicula.get();

		// Actualiza otros campos según sea necesario
		pelicula.setTitulo(pelicula.getTitulo());
		pelicula.setDuracion(pelicula.getDuracion());
		pelicula.setSinopsis(pelicula.getSinopsis());

		return Optional.of(peliculaRepository.save(pelicula));
	}

	// Eliminar un pelicula por ID
	public boolean delete(Integer id) {
		if (!peliculaRepository.existsById(id)) {
			return false;
		}

		peliculaRepository.deleteById(id);
		return true;
	}
}
