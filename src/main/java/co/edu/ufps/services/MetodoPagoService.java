package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Pedido;
import co.edu.ufps.entities.MetodoPago;
import co.edu.ufps.entities.Pedido;
import co.edu.ufps.entities.MetodoPago;
import co.edu.ufps.repositories.PedidoRepository;
import co.edu.ufps.repositories.MetodoPagoRepository;

@Service
public class MetodoPagoService {

	@Autowired
	MetodoPagoRepository metodoPagoRepository;
	@Autowired
	public PedidoRepository pedidoRepository;

	public List<MetodoPago> list() {
		return metodoPagoRepository.findAll();
	}

	public MetodoPago create(MetodoPago metodoPago) {
		return metodoPagoRepository.save(metodoPago);
	}

//	public Optional<MetodoPago> getByDescripcion(String descripcion) {
//		return metodoPagoRepository.findByDescripcion(descripcion);
//	}
//
//	public Optional<List<Pedido>> getPedidosByDescripcion(String descripcion) {
//		Optional<MetodoPago> metodoPago = metodoPagoRepository.findByDescripcion(descripcion);
//		return metodoPago.map(MetodoPago::getPedidos);
//	}

//	/**
//	 * Actualiza la descripción de un método de pago existente.
//	 * 
//	 * @param descripcion       La descripción del método de pago a actualizar.
//	 * @param metodoPagoDetails Los nuevos detalles del metodo de pago.
//	 * @return Un Optional con el MetodoPago actualizado si se encuentra, o vacío si
//	 *         no.
//	 */
//	public Optional<MetodoPago> update(String descripcion, MetodoPago metodoPagoDetails) {
//		Optional<MetodoPago> optionalMetodoPago = metodoPagoRepository.findByDescripcion(descripcion);
//		if (!optionalMetodoPago.isPresent()) {
//			return Optional.empty();
//		}
//
//		MetodoPago metodoPago = optionalMetodoPago.get();
//		metodoPago.setDescripcion(metodoPagoDetails.getDescripcion());
//
//		return Optional.of(metodoPagoRepository.save(metodoPago));
//	}

//	/**
//	 * Elimina un método de pago de la base de datos basado en su descripción.
//	 * 
//	 * @param descripcion La descripción del método de pago a eliminar.
//	 * @return true si el método de pago fue eliminado, false si no se encontró.
//	 */
//	public boolean deleteByDescripcion(String descripcion) {
//		Optional<MetodoPago> metodoPago = metodoPagoRepository.findByDescripcion(descripcion);
//		if (metodoPago.isPresent()) {
//			metodoPagoRepository.delete(metodoPago.get());
//			return true;
//		}
//		return false;
//	}
	
	public MetodoPago addPedido(Integer id, Integer pedidoId) {

		Optional<MetodoPago> metodoPagoOpt = metodoPagoRepository.findById(id);

		if (metodoPagoOpt.isPresent()) {
			MetodoPago metodoPago = metodoPagoOpt.get();
			Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);
			if (pedidoOpt.isPresent()) {
				metodoPago.addPedido(pedidoOpt.get());
			}
			return metodoPagoRepository.save(metodoPago);
		}
		return null;
	}

}
