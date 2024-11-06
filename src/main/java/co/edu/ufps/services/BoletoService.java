package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.Asiento;
import co.edu.ufps.repositories.BoletoRepository;
import co.edu.ufps.repositories.AsientoRepository;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private AsientoRepository asientoRepository;

    /**
     * Lista todos los boletos en el sistema.
     * 
     * @return Lista de todos los boletos.
     */
    public List<Boleto> list() {
        return boletoRepository.findAll();
    }

    /**
     * Crea un nuevo boleto.
     * 
     * @param boleto El boleto a crear.
     * @return El boleto creado.
     */
    public Boleto create(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    /**
     * Obtiene un boleto por su ID.
     * 
     * @param id ID del boleto.
     * @return El boleto, si está presente.
     */
    public Optional<Boleto> getById(Integer id) {
        return boletoRepository.findById(id);
    }

    /**
     * Actualiza un boleto existente con los detalles proporcionados.
     * 
     * @param id            ID del boleto a actualizar.
     * @param boletoDetails Detalles actualizados del boleto.
     * @return El boleto actualizado, si está presente.
     */
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

    /**
     * Elimina un boleto por su ID.
     * 
     * @param id ID del boleto a eliminar.
     * @return true si el boleto fue eliminado, false si no existe.
     */
    public boolean delete(Integer id) {
        if (!boletoRepository.existsById(id)) {
            return false;
        }
        boletoRepository.deleteById(id);
        return true;
    }

    /**
     * Asocia un asiento a un boleto.
     * 
     * @param boletoId ID del boleto.
     * @param asientoId ID del asiento a asociar.
     * @return El boleto actualizado con el asiento asociado, si el boleto existe.
     */
    public Boleto addAsientoToBoleto(Integer boletoId, Integer asientoId) {
        Optional<Boleto> boletoOpt = boletoRepository.findById(boletoId);
        if (boletoOpt.isPresent()) {
            Boleto boleto = boletoOpt.get();
            Optional<Asiento> asientoOpt = asientoRepository.findById(asientoId);
            asientoOpt.ifPresent(boleto::setAsiento);
            return boletoRepository.save(boleto);
        }
        return null;
    }


}
