package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Boleto;
import co.edu.ufps.entities.CategoriaBoleto;
import co.edu.ufps.repositories.BoletoRepository;
import co.edu.ufps.repositories.CategoriaBoletoRepository;

@Service
public class CategoriaBoletoService {

    @Autowired
    private CategoriaBoletoRepository categoriaBoletoRepository;

    @Autowired
    private BoletoRepository boletoRepository;

    public List<CategoriaBoleto> list() {
        return categoriaBoletoRepository.findAll();
    }


    public CategoriaBoleto create(CategoriaBoleto categoriaBoleto) {
        return categoriaBoletoRepository.save(categoriaBoleto);
    }


    public Optional<CategoriaBoleto> getById(Integer id) {
        return categoriaBoletoRepository.findById(id);
    }

    public Optional<CategoriaBoleto> update(Integer id, CategoriaBoleto categoriaBoletoDetails) {
        Optional<CategoriaBoleto> optionalCategoriaBoleto = categoriaBoletoRepository.findById(id);
        if (!optionalCategoriaBoleto.isPresent()) {
            return Optional.empty();
        }

        CategoriaBoleto categoriaBoleto = optionalCategoriaBoleto.get();
        categoriaBoleto.setDescripcion(categoriaBoletoDetails.getDescripcion());
        categoriaBoleto.setPrecio(categoriaBoletoDetails.getPrecio());

        return Optional.of(categoriaBoletoRepository.save(categoriaBoleto));
    }

    public boolean delete(Integer id) {
        if (!categoriaBoletoRepository.existsById(id)) {
            return false;
        }
        categoriaBoletoRepository.deleteById(id);
        return true;
    }

    public List<Boleto> getBoletosByCategoria(Integer categoriaId) {
        Optional<CategoriaBoleto> categoriaBoletoOpt = categoriaBoletoRepository.findById(categoriaId);
        if (categoriaBoletoOpt.isPresent()) {
            return categoriaBoletoOpt.get().getBoletos();
        }
        return List.of();  
    }

    public Optional<CategoriaBoleto> addBoletoToCategoria(Integer categoriaId, Boleto boleto) {
        Optional<CategoriaBoleto> categoriaBoletoOpt = categoriaBoletoRepository.findById(categoriaId);
        if (categoriaBoletoOpt.isPresent()) {
            CategoriaBoleto categoriaBoleto = categoriaBoletoOpt.get();
            boleto.setCategoriaBoleto(categoriaBoleto);     
        }
        return Optional.empty();  
    }
}
