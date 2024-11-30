package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asiento; 
import co.edu.ufps.entities.Sala;
import co.edu.ufps.repositories.AsientoRepository; 
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
    
    public Optional<Asiento> getById(Integer id) {
        return asientoRepository.findById(id);
    }
    
    public Optional<Asiento> update(Integer salaId, String letra, String numeroAsiento, Asiento asientoDetails) {
        
    	    
    	 System.out.println("Verificando ID de sala: " + salaId);
    	 
    	 Sala sala = salaRepository.findById(salaId).orElseThrow(() -> 
         new RuntimeException("Empleado no encontrado con ID: " + salaId)
     );
    	 
    	 Optional<Asiento> optionalAsiento = asientoRepository.findBySalaIdAndLetraAndNumeroAsiento(salaId, letra, numeroAsiento);

    	     
    	    if (!optionalAsiento.isPresent()) {
    	        return Optional.empty();   
    	    }
    	    
    	    Asiento asiento = optionalAsiento.get();
 
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
         
        return Optional.of(asientoRepository.save(asiento));
    }



    public boolean deleteBySalaLetraNumero(Integer salaId, String letra, String numeroAsiento) {
        Optional<Asiento> asientoOpt = asientoRepository.findBySalaIdAndLetraAndNumeroAsiento(salaId, letra, numeroAsiento);
        
        if (asientoOpt.isPresent()) {
             asientoRepository.delete(asientoOpt.get());
            return true;  
        }
        return false;  
    }
    
    public boolean isAvailable(Integer salaId, String letra, String numeroAsiento) {
        Optional<Asiento> asientoOpt = asientoRepository.findBySalaIdAndLetraAndNumeroAsiento(salaId, letra, numeroAsiento);
         
        return asientoOpt.isPresent() && "Disponible".equalsIgnoreCase(asientoOpt.get().getEstado().getDescripcion());
    }
    
    public List<Asiento> findBySalaId(Integer salaId){
		return asientoRepository.findBySalaId(salaId);
    }
     
}