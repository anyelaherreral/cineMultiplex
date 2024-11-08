package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.PedidoSnackPromocion;
import co.edu.ufps.repositories.PedidoSnackPromocionRepository; // Asegúrate de importar el repositorio correspondiente

@Service
public class PedidoSnackPromocionService {

    @Autowired
    private PedidoSnackPromocionRepository pedidoSnackPromocionRepository;

    public List<PedidoSnackPromocion> list() {
        return pedidoSnackPromocionRepository.findAll();
    }


    public PedidoSnackPromocion create(PedidoSnackPromocion pedidoSnackPromocion) {
        return pedidoSnackPromocionRepository.save(pedidoSnackPromocion);
    }

    public Optional<PedidoSnackPromocion> getById(Integer id) {
        return pedidoSnackPromocionRepository.findById(id);
    }

    public Optional<PedidoSnackPromocion> update(Integer id, PedidoSnackPromocion pedidoSnackPromocionDetails) {
        Optional<PedidoSnackPromocion> optionalPedidoSnackPromocion = pedidoSnackPromocionRepository.findById(id);
        if (!optionalPedidoSnackPromocion.isPresent()) {
            return Optional.empty();
        }

        PedidoSnackPromocion pedidoSnackPromocion = optionalPedidoSnackPromocion.get();
        pedidoSnackPromocion.setPromSnack(pedidoSnackPromocionDetails.getPromSnack());
        pedidoSnackPromocion.setPedidoSnack(pedidoSnackPromocionDetails.getPedidoSnack());
        pedidoSnackPromocion.setSnacks(pedidoSnackPromocionDetails.getSnacks());

        return Optional.of(pedidoSnackPromocionRepository.save(pedidoSnackPromocion));
    }

    public boolean delete(Integer id) {
        if (!pedidoSnackPromocionRepository.existsById(id)) {
            return false;
        }
        pedidoSnackPromocionRepository.deleteById(id);
        return true;
    }
}
