package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.ufps.entities.Pelicula;
import co.edu.ufps.repositories.FuncionRepository;
import co.edu.ufps.repositories.PeliculaRepository;

@Service
public class PeliculaService {

	@Autowired
	PeliculaRepository peliculaRepository;

	@Autowired
	FuncionRepository funcionRepository;

	public List<Pelicula> list() {
		return peliculaRepository.findAll();
	}

	public Pelicula create(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}

	public Optional<Pelicula> getByNombre(String titulo) {
		return peliculaRepository.findByTitulo(titulo);
	}
 
	public Optional<Pelicula> update(Integer id, Pelicula peliculaDetails) {
		Optional<Pelicula> optionalpelicula = peliculaRepository.findById(id);
		if (!optionalpelicula.isPresent()) {
			return Optional.empty();
		}

		Pelicula pelicula = optionalpelicula.get();
 
		pelicula.setTitulo(peliculaDetails.getTitulo());
		pelicula.setDuracion(peliculaDetails.getDuracion());
		pelicula.setSinopsis(peliculaDetails.getSinopsis());

		return Optional.of(peliculaRepository.save(pelicula));
	}
	 
		public Optional<Pelicula> update(String titulo, Pelicula peliculaDetails) {
			Optional<Pelicula> optionalpelicula = peliculaRepository.findByTitulo(titulo);
			if (!optionalpelicula.isPresent()) {
				return Optional.empty();
			}

			Pelicula pelicula = optionalpelicula.get();
 
			pelicula.setTitulo(peliculaDetails.getTitulo());
			pelicula.setDuracion(peliculaDetails.getDuracion());
			pelicula.setSinopsis(peliculaDetails.getSinopsis());

			return Optional.of(peliculaRepository.save(pelicula));
		}
		
	public boolean tieneFuncionesAsociadas(Integer id) {
        return funcionRepository.existsByPeliculaId(id);
    }

    public boolean delete(Integer id) {
        if (!peliculaRepository.existsById(id)) {
            return false;
        }
        peliculaRepository.deleteById(id);
        return true;
    }

	

}
