package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.Estado;
import co.edu.ufps.entities.CategoriaBoleto;
import co.edu.ufps.entities.Funcion;
import co.edu.ufps.entities.Asiento;
import co.edu.ufps.repositories.BoletoRepository;
import co.edu.ufps.repositories.EstadoRepository;
import co.edu.ufps.repositories.AsientoRepository;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private AsientoRepository asientoRepository;
    
    @Autowired
    private EstadoRepository estadoRepository;


    public List<Boleto> list() {
        return boletoRepository.findAll();
    }

    public Boleto create(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public Optional<Boleto> getById(Integer id) {
        return boletoRepository.findById(id);
    }
    
    public Optional<Boleto> asignarAsientoABoleto(Integer boletoId, Integer salaId, String letra, String numero) {
        // Buscar el boleto
        Optional<Boleto> boletoOpt = boletoRepository.findById(boletoId);
        if (boletoOpt.isEmpty()) {
            return Optional.empty(); // No existe el boleto
        }

        // Verificar que el asiento existe y está disponible
        Optional<Asiento> asientoOpt = asientoRepository.findBySalaIdAndLetraAndNumeroAsientoAndEstadoDescripcion(salaId, letra, numero, "DISPONIBLE");
        if (asientoOpt.isEmpty()) {
            return Optional.empty(); // No se encontró un asiento disponible
        }

        // Cambiar estado del asiento a "OCUPADO"
        Asiento asiento = asientoOpt.get();
        Optional<Estado> estadoOcupadoOpt = estadoRepository.findByDescripcion("OCUPADO");
        if (estadoOcupadoOpt.isEmpty()) {
            return Optional.empty(); // No existe el estado "OCUPADO"
        }

        asiento.setEstado(estadoOcupadoOpt.get());
        asientoRepository.save(asiento); // Guardar el cambio de estado del asiento

        // Asignar asiento al boleto
        Boleto boleto = boletoOpt.get();
        boleto.setAsiento(asiento);
        return Optional.of(boletoRepository.save(boleto)); // Guardar el boleto actualizado
    }
    

    public Optional<Boleto> update(Integer id, Boleto boletoDetails) {
        Optional<Boleto> optionalBoleto = boletoRepository.findById(id);
        if (!optionalBoleto.isPresent()) {
            return Optional.empty();
        }

        Boleto boleto = optionalBoleto.get();
        boleto.setAsiento(boletoDetails.getAsiento());
        boleto.setFuncion(boletoDetails.getFuncion());
//        boleto.setCategoriaBoleto(boletoDetails.getCategoriaBoleto());

        return Optional.of(boletoRepository.save(boleto));
    }

    public boolean delete(Integer id) {
        if (!boletoRepository.existsById(id)) {
            return false;
        }
        boletoRepository.deleteById(id);
        return true;
    }
    
    public Boleto addAsientoToBoleto(Integer boletoId, Integer asientoId) {
        // Buscar el boleto
        Optional<Boleto> boletoOpt = boletoRepository.findById(boletoId);
        if (boletoOpt.isEmpty()) {
            throw new IllegalArgumentException("El boleto con ID " + boletoId + " no existe.");
        }
     // Buscar el asiento
        Optional<Asiento> asientoOpt = asientoRepository.findById(asientoId);
        if (asientoOpt.isEmpty()) {
            throw new IllegalArgumentException("El asiento con ID " + asientoId + " no existe.");
        }
        Boleto boleto = boletoOpt.get();
        Asiento asiento = asientoOpt.get();

        // Verificar si el asiento está disponible
        if (!"Disponible".equalsIgnoreCase(asiento.getEstado().getDescripcion())) {
            throw new IllegalStateException("El asiento no está disponible.");
        }

        // Asignar el asiento al boleto
        boleto.setAsiento(asiento);

        // Cambiar el estado del asiento a 'Ocupado'
        asiento.getEstado().setDescripcion("Ocupado");
        asientoRepository.save(asiento);

        // Guardar el boleto actualizado
        return boletoRepository.save(boleto);
    }
    
    
}



