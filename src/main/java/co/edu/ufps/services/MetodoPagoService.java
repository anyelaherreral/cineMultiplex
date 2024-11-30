package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
