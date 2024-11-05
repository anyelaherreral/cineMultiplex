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

    /**
     * Lista todos los PedidoSnackPromocion en el sistema.
     * 
     * @return Lista de todos los PedidoSnackPromocion.
     */
    public List<PedidoSnackPromocion> list() {
        return pedidoSnackPromocionRepository.findAll();
    }

    /**
     * Crea un nuevo PedidoSnackPromocion.
     * 
     * @param pedidoSnackPromocion El PedidoSnackPromocion a crear.
     * @return El PedidoSnackPromocion creado.
     */
    public PedidoSnackPromocion create(PedidoSnackPromocion pedidoSnackPromocion) {
        return pedidoSnackPromocionRepository.save(pedidoSnackPromocion);
    }

    /**
     * Obtiene un PedidoSnackPromocion por su ID.
     * 
     * @param id ID del PedidoSnackPromocion.
     * @return El PedidoSnackPromocion, si está presente.
     */
    public Optional<PedidoSnackPromocion> getById(Integer id) {
        return pedidoSnackPromocionRepository.findById(id);
    }

    /**
     * Actualiza un PedidoSnackPromocion existente con los detalles proporcionados.
     * 
     * @param id                   ID del PedidoSnackPromocion a actualizar.
     * @param pedidoSnackPromocion Detalles actualizados del PedidoSnackPromocion.
     * @return El PedidoSnackPromocion actualizado, si está presente.
     */
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

    /**
     * Elimina un PedidoSnackPromocion por su ID.
     * 
     * @param id ID del PedidoSnackPromocion a eliminar.
     * @return true si el PedidoSnackPromocion fue eliminado, false si no existe.
     */
    public boolean delete(Integer id) {
        if (!pedidoSnackPromocionRepository.existsById(id)) {
            return false;
        }
        pedidoSnackPromocionRepository.deleteById(id);
        return true;
    }
}
