package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Pelicula;
import co.edu.ufps.entities.Genero;
import co.edu.ufps.repositories.PeliculaRepository;
import co.edu.ufps.repositories.GeneroRepository;


@Service
public class GeneroService {

	@Autowired
	GeneroRepository generoRepository;
	@Autowired
	public PeliculaRepository peliculaRepository;

	
	public List<Genero> list() {
		return generoRepository.findAll();
	}
	
	public Genero create(Genero genero) {
		return generoRepository.save(genero);
	}

	// Obtener un genero por ID
	public Optional<Genero> getById(Integer id) {
		return generoRepository.findById(id);
	}

	// Actualizar un genero existente
	public Optional<Genero> update(Integer id, Genero generoDetails) {
		Optional<Genero> optionalgenero = generoRepository.findById(id);
		if (!optionalgenero.isPresent()) {
			return Optional.empty();
		}

		Genero genero = optionalgenero.get();

		// Actualiza otros campos según sea necesario
		genero.setDescripcion(genero.getDescripcion());

		return Optional.of(generoRepository.save(genero));
	}

	// Eliminar un genero por ID
	public boolean delete(Integer id) {
		if (!generoRepository.existsById(id)) {
			return false;
		}

		generoRepository.deleteById(id);
		return true;
	}
	
	public Genero addPelicula(Integer id, Integer peliculaId) {

		Optional<Genero> generoOpt = generoRepository.findById(id);

		if (generoOpt.isPresent()) {
			Genero genero = generoOpt.get();
			Optional<Pelicula> peliculaOpt = peliculaRepository.findById(peliculaId);
			if (peliculaOpt.isPresent()) {
				genero.addPelicula(peliculaOpt.get());
			}
			return generoRepository.save(genero);
		}
		return null;
	}
}
