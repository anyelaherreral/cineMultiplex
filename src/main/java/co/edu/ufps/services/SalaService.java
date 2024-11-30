package co.edu.ufps.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Pelicula;
import co.edu.ufps.entities.Sala; 
import co.edu.ufps.repositories.AsientoRepository;
import co.edu.ufps.repositories.FuncionRepository;
import co.edu.ufps.repositories.PeliculaRepository;
import co.edu.ufps.repositories.SalaRepository;


@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	FuncionRepository funcionRepository;
	
	@Autowired
	AsientoRepository asientoRepository;
	
	@Autowired
	PeliculaRepository peliculaRepository;
	
	public List<Sala> list() {
		return salaRepository.findAll();
	}
	
	public Sala create(Sala sala) {
		return salaRepository.save(sala);
	}
 
	public Optional<Sala> getById(Integer id) {
		return salaRepository.findById(id);
	}
	
	public Optional<List<Asiento>> getAsientosBySalaId(Integer salaId) {
        Optional<Sala> salaOpt = salaRepository.findById(salaId);
        return salaOpt.map(Sala::getAsientos);  
    }
 
	public Optional<Sala> update(Integer id, Sala salaDetails) {
		Optional<Sala> optionalsala = salaRepository.findById(id);
		if (!optionalsala.isPresent()) {
			return Optional.empty();
		}

		Sala sala = optionalsala.get();
 
		sala.setNum_asientos(salaDetails.getNum_asientos());
		sala.setTipoProyeccion(salaDetails.getTipoProyeccion());

		return Optional.of(salaRepository.save(sala));
	}
 
	public boolean delete(Integer id) {
		if (!salaRepository.existsById(id)) {
			return false;
		}

		salaRepository.deleteById(id);
		return true;
	}
	
	public Sala addFuncion(Integer id, Integer funcionId) {

		Optional<Sala> salaOpt = salaRepository.findById(id);

		if (salaOpt.isPresent()) {
			Sala sala = salaOpt.get();
			Optional<Funcion> funcionOpt = funcionRepository.findById(funcionId);
			if (funcionOpt.isPresent()) {
				sala.addFuncion(funcionOpt.get());
			}
			return salaRepository.save(sala);
		}
		return null;
	}
	
	public Sala addAsiento(Integer id, Integer asientoId) {

		Optional<Sala> salaOpt = salaRepository.findById(id);

		if (salaOpt.isPresent()) {
			Sala sala = salaOpt.get();
			Optional<Asiento> asientoOpt = asientoRepository.findById(asientoId);
			if (asientoOpt.isPresent()) {
				sala.addAsiento(asientoOpt.get());
			}
			return salaRepository.save(sala);
		}
		return null;
	}
	
	
	public List<Sala> obtenerSalasDisponibles(LocalDate fecha, String hora, Integer peliculaId) {  
        LocalTime horaInicio = LocalTime.parse(hora);
        LocalDateTime fechaHoraInicio = LocalDateTime.of(fecha, horaInicio); 
        Pelicula pelicula = peliculaRepository.findById(peliculaId)
            .orElseThrow(() -> new RuntimeException("Película no encontrada"));

     
        int duracionPelicula = Integer.parseInt(pelicula.getDuracion().split(":")[0]) * 60 
                               + Integer.parseInt(pelicula.getDuracion().split(":")[1]);

        LocalDateTime horaFin = fechaHoraInicio.plusMinutes(duracionPelicula);
        List<Funcion> funciones = funcionRepository.findByFecha(fecha);
        List<Sala> salasDisponibles = new ArrayList<>();
        for (Sala sala : salaRepository.findAll()) {
            boolean salaDisponible = true;

            for (Funcion funcionExistente : funciones) {
                if (funcionExistente.getSala().equals(sala)) {
                    LocalDateTime funcionInicioExistente = LocalDateTime.of(funcionExistente.getFecha(), 
                                                                            LocalTime.parse(funcionExistente.getHorario()));
                    int duracionExistente = Integer.parseInt(funcionExistente.getPelicula().getDuracion().split(":")[0]) * 60 
                                            + Integer.parseInt(funcionExistente.getPelicula().getDuracion().split(":")[1]);
                    
                    LocalDateTime funcionFinExistente = funcionInicioExistente.plusMinutes(duracionExistente);

                    if (fechaHoraInicio.isBefore(funcionFinExistente) && horaFin.isAfter(funcionInicioExistente)) {
                        salaDisponible = false;  
                        break; 
                    }
                }
            }
            if (salaDisponible) {
                salasDisponibles.add(sala);
            }
        }
        return salasDisponibles;
    }
	
}
