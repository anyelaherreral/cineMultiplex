package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.CategoriaBoletoPromocion;
import co.edu.ufps.repositories.CategoriaBoletoPromocionRepository;

@Service
public class CategoriaBoletoPromocionService {

    @Autowired
    private CategoriaBoletoPromocionRepository categoriaBoletoPromocionRepository;

    public List<CategoriaBoletoPromocion> list() {
        return categoriaBoletoPromocionRepository.findAll();
    }

  
    public CategoriaBoletoPromocion create(CategoriaBoletoPromocion categoriaBoletoPromocion) {
        return categoriaBoletoPromocionRepository.save(categoriaBoletoPromocion);
    }

    
    public Optional<CategoriaBoletoPromocion> getById(Integer id) {
        return categoriaBoletoPromocionRepository.findById(id);
    }

   
    public Optional<CategoriaBoletoPromocion> update(Integer id, CategoriaBoletoPromocion categoriaBoletoPromocionDetails) {
        Optional<CategoriaBoletoPromocion> optionalPromocion = categoriaBoletoPromocionRepository.findById(id);
        if (!optionalPromocion.isPresent()) {
            return Optional.empty();
        }

        CategoriaBoletoPromocion categoriaBolProm = optionalPromocion.get();
        categoriaBolProm.setDescuento(categoriaBoletoPromocionDetails.getDescuento());
        categoriaBolProm.setCategoriaBoleto(categoriaBoletoPromocionDetails.getCategoriaBoleto());
        categoriaBolProm.setPromocion(categoriaBoletoPromocionDetails.getPromocion());

        return Optional.of(categoriaBoletoPromocionRepository.save(categoriaBolProm));
    }

}
