package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.Empleado;
import co.edu.ufps.entities.Estado;
import co.edu.ufps.entities.Sala;
import co.edu.ufps.repositories.AsientoRepository;
import co.edu.ufps.repositories.BoletoRepository;
import co.edu.ufps.repositories.EstadoRepository;
import co.edu.ufps.repositories.SalaRepository;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    
    @Autowired
    private SalaRepository salaRepository; 

    public List<Asiento> list() {
        return asientoRepository.findAll();
    }

    public Asiento create(Asiento asiento) {
        return asientoRepository.save(asiento);
    }
    
    public Optional<Asiento> getAsiento(Integer salaId, String letra, String numeroAsiento) {
        return asientoRepository.findBySalaIdAndLetraAndNumeroAsiento(salaId, letra, numeroAsiento);
    }
    
    
  
    
    
    
    public Optional<Asiento> update(Integer salaId, String letra, String numeroAsiento, Asiento asientoDetails) {
        // Buscar el asiento por salaId, letra y numeroAsiento
    	
    	    
    	 System.out.println("Verificando ID de sala: " + salaId);
    	 
    	 Sala sala = salaRepository.findById(salaId).orElseThrow(() -> 
         new RuntimeException("Empleado no encontrado con ID: " + salaId)
     );
    	 
    	 Optional<Asiento> optionalAsiento = asientoRepository.findBySalaIdAndLetraAndNumeroAsiento(salaId, letra, numeroAsiento);

    	    
    	    // Buscar el asiento por los valores que has proporcionado
    	    if (!optionalAsiento.isPresent()) {
    	        return Optional.empty();  // Si no existe, devolver Optional vacío
    	    }
    	    
    	    Asiento asiento = optionalAsiento.get();

    	    // Verificar si se pasó un valor para actualizar y solo actualizar esos campos
    	    if (asientoDetails.getLetra() != null && !asientoDetails.getLetra().isEmpty()) {
    	        asiento.setLetra(asientoDetails.getLetra());
    	    }
    	    if (asientoDetails.getNumeroAsiento() != null && !asientoDetails.getNumeroAsiento().isEmpty()) {
    	        asiento.setNumeroAsiento(asientoDetails.getNumeroAsiento());
    	    }
    	    if (asientoDetails.getEstado() != null) {
    	        asiento.setEstado(asientoDetails.getEstado());
    	    }
    	    if (asientoDetails.getSala() != null) {
    	        asiento.setSala(asientoDetails.getSala());
    	    }
        
        // Guardamos y devolvemos el asiento actualizado
        return Optional.of(asientoRepository.save(asiento));
    }



    public boolean deleteBySalaLetraNumero(Integer salaId, String letra, String numeroAsiento) {
        Optional<Asiento> asientoOpt = asientoRepository.findBySalaIdAndLetraAndNumeroAsiento(salaId, letra, numeroAsiento);
        
        if (asientoOpt.isPresent()) {
            // Eliminar el asiento encontrado
            asientoRepository.delete(asientoOpt.get());
            return true; // Se eliminó el asiento
        }
        return false; // No se encontró el asiento para eliminar
    }
    
    public boolean isAvailable(Integer salaId, String letra, String numeroAsiento) {
        Optional<Asiento> asientoOpt = asientoRepository.findBySalaIdAndLetraAndNumeroAsiento(salaId, letra, numeroAsiento);
        
        // Verificar si el asiento existe y está disponible
        return asientoOpt.isPresent() && "Disponible".equalsIgnoreCase(asientoOpt.get().getEstado().getDescripcion());
    }
    
    public List<Asiento> findBySalaId(Integer salaId){
		return asientoRepository.findBySalaId(salaId);
    }
    
//    public List<Asiento> obtenerAsientosPorSalaYFuncion(Integer salaId, Integer funcionId) {
//        return asientoRepository.findBySalaAndFuncion(salaId, funcionId);
//    }
    
}