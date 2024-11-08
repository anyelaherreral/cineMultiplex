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

    public List<Boleto> list() {
        return boletoRepository.findAll();
    }

    public Boleto create(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public Optional<Boleto> getById(Integer id) {
        return boletoRepository.findById(id);
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
