package co.edu.ufps.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.Estado; 
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Asiento;
import co.edu.ufps.repositories.BoletoRepository;
import co.edu.ufps.repositories.EstadoRepository;
import co.edu.ufps.repositories.FuncionRepository;
import co.edu.ufps.repositories.AsientoRepository;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private AsientoRepository asientoRepository;
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private FuncionRepository funcionRepository;


    public List<Boleto> list() {
        return boletoRepository.findAll();
    }

    public Boleto create(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public Optional<Boleto> getById(Integer id) {
        return boletoRepository.findById(id);
    }
    
    public List<Asiento> getAsientosByFuncionId(Integer funcionId) {
        return boletoRepository.findAsientosByFuncionId(funcionId);
    }
    
    
    public Optional<Boleto> asignarAsientoABoleto(Integer boletoId, Integer salaId, String letra, String numero) {
         Optional<Boleto> boletoOpt = boletoRepository.findById(boletoId);
        if (boletoOpt.isEmpty()) {
            return Optional.empty();  
        }

         Optional<Asiento> asientoOpt = asientoRepository.findBySalaIdAndLetraAndNumeroAsientoAndEstadoDescripcion(salaId, letra, numero, "DISPONIBLE");
        if (asientoOpt.isEmpty()) {
            return Optional.empty();  
        }
 
        Asiento asiento = asientoOpt.get();
        Optional<Estado> estadoOcupadoOpt = estadoRepository.findByDescripcion("OCUPADO");
        if (estadoOcupadoOpt.isEmpty()) {
            return Optional.empty();  
        }

        asiento.setEstado(estadoOcupadoOpt.get());
        asientoRepository.save(asiento);  

    
        Boleto boleto = boletoOpt.get();
        boleto.setAsiento(asiento);
        return Optional.of(boletoRepository.save(boleto));  
    }
    

    public Optional<Boleto> update(Integer id, Boleto boletoDetails) {
        Optional<Boleto> optionalBoleto = boletoRepository.findById(id);
        if (!optionalBoleto.isPresent()) {
            return Optional.empty();
        }

        Boleto boleto = optionalBoleto.get();
        boleto.setAsiento(boletoDetails.getAsiento());
        boleto.setFuncion(boletoDetails.getFuncion());
 

        return Optional.of(boletoRepository.save(boleto));
    }

    public boolean delete(Integer id) {
        if (!boletoRepository.existsById(id)) {
            return false;
        }
        boletoRepository.deleteById(id);
        return true;
    }
    
    public Boleto addAsientoToBoleto(Integer boletoId, Integer asientoId, Integer funcionId) {
         Optional<Boleto> boletoOpt = boletoRepository.findById(boletoId);
        if (boletoOpt.isEmpty()) {
            throw new IllegalArgumentException("El boleto con ID " + boletoId + " no existe.");
        }

         Optional<Asiento> asientoOpt = asientoRepository.findById(asientoId);
        if (asientoOpt.isEmpty()) {
            throw new IllegalArgumentException("El asiento con ID " + asientoId + " no existe.");
        }

         Optional<Funcion> funcionOpt = funcionRepository.findById(funcionId);
        if (funcionOpt.isEmpty()) {
            throw new IllegalArgumentException("La función con ID " + funcionId + " no existe.");
        }

        Boleto boleto = boletoOpt.get();
        Asiento asiento = asientoOpt.get();
        Funcion funcion = funcionOpt.get();

         boolean asientoOcupado = boletoRepository.existsByFuncionIdAndAsientoId(funcionId, asientoId);
        if (asientoOcupado) {
            throw new IllegalStateException("El asiento " + asientoId + " ya está ocupado para la función " + funcionId + ".");
        }

         boleto.setAsiento(asiento);
        boleto.setFuncion(funcion);

         asiento.getEstado().setDescripcion("Ocupado");
        asientoRepository.save(asiento);

         return boletoRepository.save(boleto);
    }
    
    public List<Boleto> createBoletos(List<Boleto> boletos) {
        List<Boleto> boletosCreados = new ArrayList<>();

         for (Boleto boleto : boletos) {
            try {
                Boleto boletoCreado = boletoRepository.save(boleto);  
                boletosCreados.add(boletoCreado);
            } catch (Exception e) {
                System.err.println("Error al crear boleto: " + e.getMessage());
            }
        }

        return boletosCreados;
    }

}



