package co.edu.ufps.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.ufps.entities.Pelicula;
import co.edu.ufps.entities.Genero;
import co.edu.ufps.repositories.PeliculaRepository;
import jakarta.transaction.Transactional;
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

	public List<Pelicula> listPeliculas(String descripcion) {
		Optional<Genero> rolOpt = generoRepository.findByDescripcion(descripcion);
		return rolOpt.map(Genero::getPeliculas).orElse(Collections.emptyList());
	}

	public Genero create(Genero genero) {
		return generoRepository.save(genero);
	}

	// Obtener un genero por ID
	public Optional<Genero> getById(Integer id) {
		return generoRepository.findById(id);
	}

	public Optional<Genero>getByDescripcion(String descripcion) {
		return generoRepository.findByDescripcion(descripcion);
	}

	// Actualizar un genero existente
	public Optional<Genero> update(Integer id, Genero generoDetails) {
		Optional<Genero> optionalgenero = generoRepository.findById(id);
		if (!optionalgenero.isPresent()) {
			return Optional.empty();
		}

		Genero genero = optionalgenero.get();

		// Actualiza otros campos según sea necesario
		genero.setDescripcion(generoDetails.getDescripcion());

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

	// Eliminar un genero por Descripcion
	@Transactional
	public boolean deleteByDescripcion(String descripcion) {
		Optional<Genero> generoOpt = generoRepository.findByDescripcion(descripcion);
		if (generoOpt.isPresent()) {
			generoRepository.deleteByDescripcion(descripcion);
			return true;
		}
		return false;
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
