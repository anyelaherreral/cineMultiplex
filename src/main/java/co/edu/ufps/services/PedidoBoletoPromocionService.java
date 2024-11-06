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

    /**
     * Lista todos los PedidoBoletoPromocion en el sistema.
     * 
     * @return Lista de todos los PedidoBoletoPromocion.
     */
    public List<PedidoBoletoPromocion> list() {
        return pedidoBoletoPromocionRepository.findAll();
    }

    /**
     * Crea un nuevo PedidoBoletoPromocion.
     * 
     * @param pedidoBoletoPromocion El PedidoBoletoPromocion a crear.
     * @return El PedidoBoletoPromocion creado.
     */
    public PedidoBoletoPromocion create(PedidoBoletoPromocion pedidoBoletoPromocion) {
        return pedidoBoletoPromocionRepository.save(pedidoBoletoPromocion);
    }

    /**
     * Obtiene un PedidoBoletoPromocion por su ID.
     * 
     * @param id ID del PedidoBoletoPromocion.
     * @return El PedidoBoletoPromocion, si está presente.
     */
    public Optional<PedidoBoletoPromocion> getById(Integer id) {
        return pedidoBoletoPromocionRepository.findById(id);
    }

    /**
     * Actualiza un PedidoBoletoPromocion existente con los detalles proporcionados.
     * 
     * @param id                   ID del PedidoBoletoPromocion a actualizar.
     * @param pedidoBoletoPromocion Detalles actualizados del PedidoBoletoPromocion.
     * @return El PedidoBoletoPromocion actualizado, si está presente.
     */
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

    /**
     * Elimina un PedidoBoletoPromocion por su ID.
     * 
     * @param id ID del PedidoBoletoPromocion a eliminar.
     * @return true si el PedidoBoletoPromocion fue eliminado, false si no existe.
     */
    public boolean delete(Integer id) {
        if (!pedidoBoletoPromocionRepository.existsById(id)) {
            return false;
        }
        pedidoBoletoPromocionRepository.deleteById(id);
        return true;
    }
}
