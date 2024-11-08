package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.PedidoBoletoPromocion;
import co.edu.ufps.repositories.PedidoBoletoPromocionRepository; // Asegúrate de importar el repositorio correspondiente

@Service
public class PedidoBoletoPromocionService {

    @Autowired
    private PedidoBoletoPromocionRepository pedidoBoletoPromocionRepository;

    public List<PedidoBoletoPromocion> list() {
        return pedidoBoletoPromocionRepository.findAll();
    }

    public PedidoBoletoPromocion create(PedidoBoletoPromocion pedidoBoletoPromocion) {
        return pedidoBoletoPromocionRepository.save(pedidoBoletoPromocion);
    }

  
    public Optional<PedidoBoletoPromocion> getById(Integer id) {
        return pedidoBoletoPromocionRepository.findById(id);
    }

 
    public Optional<PedidoBoletoPromocion> update(Integer id, PedidoBoletoPromocion pedidoBoletoPromocionDetails) {
        Optional<PedidoBoletoPromocion> optionalPedidoBoletoPromocion = pedidoBoletoPromocionRepository.findById(id);
        if (!optionalPedidoBoletoPromocion.isPresent()) {
            return Optional.empty();
        }

        PedidoBoletoPromocion pedidoBoletoPromocion = optionalPedidoBoletoPromocion.get();
        pedidoBoletoPromocion.setProm(pedidoBoletoPromocionDetails.getProm());
        pedidoBoletoPromocion.setPedido(pedidoBoletoPromocionDetails.getPedido());
        pedidoBoletoPromocion.setBoleto(pedidoBoletoPromocionDetails.getBoleto());

        return Optional.of(pedidoBoletoPromocionRepository.save(pedidoBoletoPromocion));
    }

    public boolean delete(Integer id) {
        if (!pedidoBoletoPromocionRepository.existsById(id)) {
            return false;
        }
        pedidoBoletoPromocionRepository.deleteById(id);
        return true;
    }
}
